package com.hepeng.trace.param.track;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 *
 * method 里面的方法都是执行方法。比如说：执行 System.out.println();
 *
 * @author hp.he
 * @date 2019/8/29 17:59
 */
public class ParamTraceMethodAdaptor extends MethodVisitor {
    public ParamTraceMethodAdaptor(MethodVisitor methodVisitor) {
        super(ASM6, methodVisitor);
    }

    /**
     * 重写方法访问方法
     * */
    /**
     * 重写改写参数
     */
    @Override
    public void visitParameter(final String name, final int access) {
        System.out.println("参数名字：" + name);
        super.visitParameter(name, access);
    }


    @Override
    public void visitLdcInsn(final Object value) {
      //  System.out.println("visitLdcInsn：" + value);
        super.visitLdcInsn(value);
    }

    @Override
    public void visitVarInsn(final int opcode, final int var) {
        System.out.println("visitVarInsn：" + var);
        super.visitVarInsn(opcode,var);
    }
}
