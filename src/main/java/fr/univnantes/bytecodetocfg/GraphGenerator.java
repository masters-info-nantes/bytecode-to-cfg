package fr.univnantes.bytecodetocfg;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

import fr.univnantes.controlflowgraph.Node;

/**
 * Simple class to hide ASM backend
 */
public class GraphGenerator {
	
	/**
	 * Return control flow graph of given method
	 * in specified class
	 * @param className Class where the metho is declared
	 * @param methodName Method to analyse
	 * @return Generated graph
	 * @throws IOException Class does not exists
	 */
	public Node analyzeMethod(String className, String methodName) throws IOException{
		 InputStream in = AnalyzedClass.class.getResourceAsStream(className + ".class");
		 ClassReader classReader = new ClassReader(in);
		 ClassAnalyzer classAnalyzer = new ClassAnalyzer(methodName);
		 classReader.accept(classAnalyzer, 0);
		 
		 return classAnalyzer.getGraph();
	}
}
