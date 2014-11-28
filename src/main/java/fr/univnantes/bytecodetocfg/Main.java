package fr.univnantes.bytecodetocfg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import fr.univnantes.controlflowgraph.App;
import fr.univnantes.controlflowgraph.Arc;
import fr.univnantes.controlflowgraph.Condition;
import fr.univnantes.controlflowgraph.Instruction;
import fr.univnantes.controlflowgraph.Node;

/*
 * Run class analyzer on class which contains
 * the method we want to generate graph
 */
public class Main {
    
	public static MethodAnalyzer analyzer;
    public static void main(String[] args) throws Exception{
    	System.out.println("Your're in main method");
        InputStream in = AnalyzedClass.class.getResourceAsStream("AnalyzedClass.class");
        ClassReader classReader = new ClassReader(in);
        classReader.accept(new ClassAnalyzer(), 0);
        
        Node graph = analyzer.getGraph();
		File file = new File("html/data.js");
		Main.display(file, graph);
    }
    
    /**
	 * Generate js code to display graph in web browser
	 * 	- html folder in root directory contains vis.js library
	 *  - to see graph see html/index.html in a browser
	 *  - not sure to keep for future release
	 * @param file
	 * @throws IOException
	 */
	public static void display(File file, Node graph) throws IOException {
		
		//-----
		String path = file.getAbsolutePath();
		file.delete();
		BufferedWriter nfile = new BufferedWriter(new FileWriter(path, true));
		nfile.write("var nodes = [];\n");
		nfile.write("var edges = [];\n");
		//-----
		
		// 2 loops because all nodes must declared before be mentionned in edges
		boolean start = true;
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(graph);
		Node cur = null;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		while(queue.size() > 0) {
			cur = queue.remove();
			if(!idList.contains(cur.getId())){
				if(start){
					nfile.write("nodes.push({ id: " + cur.getId() + ", label: String(\"" + cur.getName() + "\"), title: \"Start node\" });\n");
					start = false;
				}
				else {
					nfile.write("nodes.push({ id: " + cur.getId() + ", label: String(\"" + cur.getName() + "\"), title: String(\"" + cur.getId() + "\") });\n");		
				}	
			}
			
			idList.add(cur.getId());
			
			for(Arc arc : cur.getArcs()) {
				queue.add(arc.getNext());
			}
		}
		
		queue.add(graph);
		cur = null;
		while(queue.size() > 0) {
			cur = queue.remove();			

			for(Arc arc : cur.getArcs()) {
				nfile.write("edges.push({ from: " + cur.getId() + ", to: " + arc.getNext().getId() + " });\n");
				queue.add(arc.getNext());
			}
		}
		
		nfile.close();
	}
}
