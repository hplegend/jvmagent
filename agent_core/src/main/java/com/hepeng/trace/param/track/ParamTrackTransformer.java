package com.hepeng.trace.param.track;

import com.hepeng.timerconsumer.CostClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;

/**
 * @author hp.he
 * @date 2019/8/29 17:24
 */
public class ParamTrackTransformer implements ClassFileTransformer {


    /**
     *
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        // 读取类
        // class Reader 有多个构造函数
        // 最常用的一种，当然是我们自己定位类的class文件，然后定义FileInputStream。
        // 另外一种就是给定全限定名，然ClassReader帮我们干。看看底层的实现，还是根据classLoader来定位的，不谋而合。
        try {
            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor cv = new ParamTraceClassAdaptor(cw);

            cr.accept(cv, EXPAND_FRAMES);

            return cw.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }
}
