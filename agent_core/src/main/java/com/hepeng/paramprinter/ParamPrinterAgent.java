package com.hepeng.paramprinter;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2019/1/15 18:51
 */
public class ParamPrinterAgent {
    /**
     * 动态的attach到jvm上
     */
    public static void agentmain(String args, Instrumentation inst) {
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            try {
                if (cls.getName().contains("ParamPrinterTestClass")) {
                    System.out.println("do replace");
                    inst.addTransformer(new ParamPrinterTransformer(), true);
                    inst.retransformClasses(cls);
                }
                System.out.println(cls.getName());
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }

        }

    }
}
