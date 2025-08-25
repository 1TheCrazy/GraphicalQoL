package me.onethecrazy.graphicqol.util;

import com.google.gson.Gson;
import me.onethecrazy.graphicqol.GraphicQoL;
import me.onethecrazy.graphicqol.objects.config.Config;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    public static String readJSONObjectFileContents(Path file) throws IOException{
        createFileIfNotPresent(file, "{}");

        return Files.readString(file);
    }

    public static void writeFile(Path file, String content) throws IOException{
        createFileIfNotPresent(file, "");

        Files.writeString(file, content);
    }

    public static Config loadConfig() {
        try{
            String saveContents = readJSONObjectFileContents(getConfigPath());

            Gson gson = new Gson();

            return gson.fromJson(saveContents, Config.class);
        }
        catch(Exception ex) {
            GraphicQoL.LOGGER.error("Error while loading config from disk: {0}", ex);
            return new Config();
        }
    }

    public static void createFileIfNotPresent(Path file, String emptyFileContent) throws IOException {
        Path parent = file.getParent();
        if (parent != null) Files.createDirectories(parent);

        try {
            Files.createFile(file);
            // Write Empty json, so we don't get an exception when reading the file contents and just parsing them via gson
            Files.writeString(file, emptyFileContent);
        }
        // File already exists
        catch(FileAlreadyExistsException e){ }
    }

    public static void createPaths() {
        try{
            Files.createDirectory(getDefaultPath());
            createFileIfNotPresent(getConfigPath(), "{}");
        } catch (IOException e) {
            // Ignore FileAlreadyExistsException
            if(e instanceof FileAlreadyExistsException)
                return;

            GraphicQoL.LOGGER.error("Ran into error while creating default path: {0}", e);
        }
    }

    public static Path getDefaultPath(){
        return FabricLoader.getInstance().getGameDir().resolve("." + GraphicQoL.MOD_ID);
    }

    public static Path getConfigPath(){
        return getDefaultPath().resolve(".config");
    }

    public static boolean doesFileExist(Path filePath){
        return Files.exists(filePath);
    }

    public static void writeConfig(Config save) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(save);

        writeFile(getConfigPath(), json);
    }
}
