package me.onethecrazy.graphicqol.objects.config;

public class GeneralPlayerConfig {
    // Defaults
    public static final boolean DEFAULT_HEAD_TILT = false;
    public static final boolean DEFAULT_PUMPKIN_BLUR = false;
    public static final int DEFAULT_LOW_FIRE_SHIFT = 0;

    // Instance Values
    public boolean noHeadTilt;
    public boolean noPumpkinBlur;
    public int lowFireShift;

    public GeneralPlayerConfig(){
        noHeadTilt = DEFAULT_HEAD_TILT;
        noPumpkinBlur = DEFAULT_PUMPKIN_BLUR;
        lowFireShift = DEFAULT_LOW_FIRE_SHIFT;
    }
}
