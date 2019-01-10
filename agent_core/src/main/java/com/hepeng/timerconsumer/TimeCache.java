package com.hepeng.timerconsumer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 10:46
 */
public class TimeCache {
    public static Map sStartTime = new HashMap<>();
    public static Map sEndTime = new HashMap<>();
    public static void setStartTime(String methodName, long time) {
        sStartTime.put(methodName, time);
    }
    public static void setEndTime(String methodName, long time) {
        sEndTime.put(methodName, time);
    }
    public static String getCostTime(String methodName) {
        long start = (long) sStartTime.get(methodName);
        long end = (long) sEndTime.get(methodName);
        return "method: " + methodName + " main " + Long.valueOf(end - start) + " ns";
    }
}
