package com.hepeng.paramprinter;

import org.objectweb.asm.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2019/1/15 19:05
 */
public class ParamPrinterMethodAdaptor extends MethodVisitor implements Opcodes {

    public ParamPrinterMethodAdaptor(final MethodVisitor mv) {
        super(ASM6, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("CALL " + name);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        /* do call */
        mv.visitMethodInsn(opcode, owner, name, desc, itf);


        /* System.err.println("RETURN" + name);  */
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("RETURN " + name);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitLdcInsn(final Object value) {
        System.out.println("visitLdcInsn:" + value);
        mv.visitLdcInsn(value);
    }
}