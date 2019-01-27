package com.hepeng.paramprinter;

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
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2019/1/15 18:52
 */
public class ParamPrinterTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.contains("ParamPrinterTestClass")) {

            System.out.println("tranform:"+className);

            try {
                String rootPath = this.getClass().getResource("/").getPath()+"\\"+className+".class";
            //    rootPath = rootPath.replaceAll("test", "core");

                FileInputStream is = new FileInputStream(rootPath);
                ClassReader cr = new ClassReader(is);
                ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES);
                ClassVisitor cv = new ParamPrinterClassVisitor(cw);

                cr.accept(cv, EXPAND_FRAMES);

                return cw.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        } else {
            System.out.println("load class:" + className);
            System.out.println("new !");
            return classfileBuffer;
        }

    }
}