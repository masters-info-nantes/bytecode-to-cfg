package fr.univnantes.bytecodetocfg;

import java.io.InputStream;
import java.util.LinkedList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import fr.univnantes.controlflowgraph.Arc;
import fr.univnantes.controlflowgraph.Condition;
import fr.univnantes.controlflowgraph.Instruction;
import fr.univnantes.controlflowgraph.Node;

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
        
        graphtest();
    }
    
    public static void graphtest() {
		System.out.println("Hello World!");
		Node origin,nA,nB,nC,nD;
		Arc a;
		
		/* Example :
		 * 
		 * x = 8;
		 * if(x > 8) {
		 *	  y = 6
		 * } else if(x == 8 ) {
		 *	  y = 7
		 * } else {
		 *    y = 1;
		 * }
		 * 
		 */
		
		origin = new Instruction("x = 8");
		
		nA = new Condition();
		a = new Arc(" ",nA);
		origin.addArc(a);
		
		nB = new Instruction("y = 6");
		a = new Arc("x > 8",nB);
		nA.addArc(a);
		
		nC = new Instruction("y = 7");
		a = new Arc("x == 8",nC);
		nA.addArc(a);
		
		nD = new Instruction("y = 1");
		a = new Arc(" ",nD);
		nA.addArc(a);
		
		// Print example
		System.out.println("Node["+origin+"]");
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(origin);
		Node cur = null;
		System.out.println("size ="+queue.size());
		while(queue.size() > 0) {
			cur = queue.remove();
			System.out.println(cur.toString());	
			for(Arc arc : cur.getArcs()) {
				queue.add(arc.getNext());
			}
		}
	}
}
