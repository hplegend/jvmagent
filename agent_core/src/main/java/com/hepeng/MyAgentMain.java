package com.hepeng;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/25 14:01
 */
public class MyAgentMain {

    /**
     * 动态的attach到jvm上
     */
    public static void agentmain(String args, Instrumentation inst) {



        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            try {
/*
                byte [] clsByte = transform(ClassLoader.getSystemClassLoader(),cls.getName(),cls,null,null);
                if(null != clsByte && clsByte.length >0) {
                    System.out.println("开始替换");
                    inst.redefineClasses(new ClassDefinition(cls,clsByte));

                }*/

                if(cls.getName().contains("TimeTest")) {
                    inst.addTransformer(new MyTransformer(),true);
                    inst.retransformClasses(cls);
                }


            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }

            System.out.println("enter agentmain");
        }

        while (true) {

        }

    }


    public static byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                   ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        List<String> methodMap = new ArrayList<String>() {{add("sayHello");add("sayHello2");}};

        String prefix = "\nlong startTime = System.currentTimeMillis();\n";
        String postfix = "\nlong endTime = System.currentTimeMillis();\n";

        className = className.replace("/", ".");
        if (className.contains("TimeTest")) {// 判断加载的class的包路径是不是需要监控的类

            CtClass ctclass = null;
            try {
                ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
                for (String methodName : methodMap) {

                    if(ctclass.isFrozen()){
                        ctclass.defrost();
                    }

                    String outputStr = "\nSystem.out.println(\"this method " + methodName
                            + " cost:\" +(endTime - startTime) +\"ms.\");";

                    String insertCode = "\n System.out.println(\"I am other\");";

                    CtMethod ctmethod = ctclass.getDeclaredMethod(methodName);// 得到这方法实例
                    String newMethodName = methodName + "$old";// 新定义一个方法叫做比如sayHello$old
                    ctmethod.setName(newMethodName);// 将原来的方法名字修改

                    // 创建新的方法，复制原来的方法，名字为原来的名字
                    CtMethod newMethod = CtNewMethod.copy(ctmethod, methodName, ctclass, null);

                    // 构建新的方法体
                    StringBuilder bodyStr = new StringBuilder();
                    bodyStr.append("{");
                    bodyStr.append(prefix);
                    bodyStr.append(newMethodName + "($$);\n");// 调用原有代码，类似于method();($$)表示所有的参数
                    bodyStr.append(postfix);
                    bodyStr.append(outputStr);
                    bodyStr.append(insertCode);
                    bodyStr.append("}");

                    newMethod.setBody(bodyStr.toString());// 替换新方法
                    ctclass.addMethod(newMethod);// 增加新方法
                }

                return ctclass.toBytecode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 在main函数之前执行
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println(" hello java agetnt! method premain method executed  ! ");
    }
}
