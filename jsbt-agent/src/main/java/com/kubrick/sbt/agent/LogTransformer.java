package com.kubrick.sbt.agent;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.lang.instrument.ClassFileTransformer;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogTransformer
 * @description: TODO
 * @date 2021/3/24 下午2:28
 */
public class LogTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            ClassReader cr = new ClassReader(className);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            TimeCountAdpter timeCountAdpter = new TimeCountAdpter(cw);
            cr.accept(timeCountAdpter, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
