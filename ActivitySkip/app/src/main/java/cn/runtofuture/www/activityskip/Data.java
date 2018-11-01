package cn.runtofuture.www.activityskip;

public class Data {
    private static String LIFE_CYCLE_DATA;

    public static String getLifeCycleData() {
        return LIFE_CYCLE_DATA;
    }

    public static void appendLifeCycleData(String lifeCycleData) {
        LIFE_CYCLE_DATA += lifeCycleData;
    }

    public static void setLifeCycleData(String lifeCycleData) {
        LIFE_CYCLE_DATA = lifeCycleData;
    }

}
