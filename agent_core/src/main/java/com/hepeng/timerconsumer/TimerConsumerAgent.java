package com.hepeng.timerconsumer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 10:49
 */
public class TimerConsumerAgent {
    /**
     * 动态的attach到jvm上
     */
    public static void agentmain(String args, Instrumentation inst) {
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            try {
                if (cls.getName().contains("Bazhang")) {
                    System.out.println("do replace");
                    inst.addTransformer(new TimerConsumerTransform(), true);
                    inst.retransformClasses(cls);
                }
                System.out.println(cls.getName());
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }

        }

        while (true) {

        }

    }
}
