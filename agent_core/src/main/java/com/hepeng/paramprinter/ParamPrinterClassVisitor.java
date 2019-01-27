package com.hepeng.paramprinter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2019/1/15 18:58
 */
public class ParamPrinterClassVisitor extends ClassVisitor {
    public ParamPrinterClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }


    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        return mv == null ? null : new ParamPrinterMethodAdaptor(mv);
    }

}
