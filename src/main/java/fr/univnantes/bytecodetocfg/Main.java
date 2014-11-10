package fr.univnantes.bytecodetocfg;

import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

/*
 * Run class analyzer on class which contains
 * the method we want to generate graph
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
    	System.out.println("test\n");
        InputStream in = AnalyzedClass.class.getResourceAsStream("AnalyzedClass.class");
        ClassReader classReader = new ClassReader(in);
        classReader.accept(new ClassAnalyzer(), 0);
    }
}
