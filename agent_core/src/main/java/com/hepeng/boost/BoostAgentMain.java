package com.hepeng.boost;

import com.hepeng.timerconsumer.TimerConsumerTransform;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author hp.he
 * @date 2019/8/30 14:03
 */
public class BoostAgentMain {
    /**
     * 定义入口函数
     * */
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

    }




}
