package com.hepeng.trace.param.track;

import com.hepeng.asm.ASMTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * agent的入口
 *
 * @author hp.he
 * @date 2019/8/29 17:20
 */
public class ParamTrackAgentMain {
    /**
     * 动态的attach到jvm上
     */
    public static void agentmain(String args, Instrumentation inst) {
        Class[] classes = inst.getAllLoadedClasses();


        for (Class cls : classes) {
            try {
                /**
                 * 仅仅对自定义的类进行增强
                 * */
                if (cls.getName().contains("com.hepeng")) {
                    System.out.println("do replace");

                    // 定义类切换
                    // 返回新的字节码即可
                    // so, 如果我们对直接秒插装，那么我们应该在ParamTrackTransformer中重新定义字节码
                    inst.addTransformer(new ParamTrackTransformer(), true);

                    // 执行类切换
                    inst.retransformClasses(cls);

                    System.out.println("对类：" + cls.getName() + "进行了替换");
                }
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }
        }
    }
}
