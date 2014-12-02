package fr.univnantes.bytecodetocfg;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

import fr.univnantes.controlflowgraph.Node;

public class GraphGenerator {
	
	public Node analyzeMethod(String className, String methodName) throws IOException{
		 InputStream in = AnalyzedClass.class.getResourceAsStream(className);
		 ClassReader classReader = new ClassReader(in);
		 ClassAnalyzer classAnalyzer = new ClassAnalyzer(methodName);
		 classReader.accept(classAnalyzer, 0);
		 
		 return classAnalyzer.getGraph();
	}
}
