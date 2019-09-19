package com.hepeng.trace.param.track;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * @author hp.he
 * @date 2019/8/29 17:55
 */
public class ParamTraceClassAdaptor extends ClassVisitor {

    public ParamTraceClassAdaptor(ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }

    /**
     * 重写methodVisit方法
     */
    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        mv = new AdviceAdapter(ASM5, mv, access, name, desc) {

            @Override
            public void visitMaxs(final int maxStack, final int maxLocals) {
                super.visitMaxs(maxStack + 1, maxLocals + 1);
            }


            @Override
            protected void onMethodEnter() {

                // 去字符串常量
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("method enter~~~~");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(Ljava/lang/String;)V", false);

                // 存储一个开始时间变量
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime",
                        "()J", false);
                mv.visitVarInsn(LSTORE, 2);

                // visitFieldInsn 访问成员变量
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

                // visitMethodInsn 调用方法
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis",
                        "()J", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(J)V", false);
                mv.visitMaxs(6, 6);
            }

            @Override
            protected void onMethodExit(int opcode) {


                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("method end~~~~");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(Ljava/lang/String;)V", false);

                // 去字符串常量
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime",
                        "()J", false);
                mv.visitVarInsn(LLOAD, 2);
                mv.visitInsn(LSUB);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(J)V", false);
            }


        };
        return mv;
    }


}
