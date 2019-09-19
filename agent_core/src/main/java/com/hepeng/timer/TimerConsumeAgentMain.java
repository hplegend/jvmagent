package com.hepeng.timer;

import com.hepeng.timerconsumer.TimerConsumerTransform;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author hp.he
 * @date 2019/8/30 12:18
 */
public class TimerConsumeAgentMain {

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
