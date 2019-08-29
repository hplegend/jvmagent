package com.hepeng.trace.param.track;


import com.hepeng.timerconsumer.Cost;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * @author hp.he
 * @date 2019/8/29 17:55
 */
public class ParamTraceClassAdaptor extends ClassVisitor {

    public ParamTraceClassAdaptor(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    /**
     * 重写methodVisit方法
     */
    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        mv = new AdviceAdapter(Opcodes.ASM6, mv, access, name, desc) {

            @Override
            protected void onMethodEnter() {

                // 去字符串常量
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("method enter~~~~");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(Ljava/lang/String;)V", false);

                // 定义一个局部变量
                mv.visitIntInsn(BIPUSH, 12);
                mv.visitVarInsn(ISTORE, 2);

                // visitFieldInsn 访问成员变量
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(ILOAD, 2);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(I)V", false);

                // visitFieldInsn 访问成员变量
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

                // visitMethodInsn 调用方法
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis",
                        "()J", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                        "(J)V", false);
            }

            @Override
            protected void onMethodExit(int opcode) {

            }
        };
        return mv;
    }


}
