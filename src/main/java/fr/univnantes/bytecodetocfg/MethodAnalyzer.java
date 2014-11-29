package fr.univnantes.bytecodetocfg;

import java.util.LinkedList;
import java.util.Queue;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import fr.univnantes.controlflowgraph.Arc;
import fr.univnantes.controlflowgraph.Condition;
import fr.univnantes.controlflowgraph.Instruction;
import fr.univnantes.controlflowgraph.Node;

/*
 * Method analyzer triggered during method analyze.
 * Used to create control flow graph
 */
public class MethodAnalyzer implements MethodVisitor{

	private Node graph;
	private Node currentNode;
	private boolean linkLast;
	private boolean lastCondition; // True if a condition has been found and end block not reach
	
	public MethodAnalyzer(){
		this.graph = new Instruction("Begin", "");
		this.currentNode = this.graph;
		this.linkLast = true;
		this.lastCondition = false;
	}
	
	/*
	 * Important methods :
	 * - visitCode & visitEnd : entry and exit method
	 * - visitLineNumber : go to specified line (in folder)
	 * - visitInsn (3) : met un zéro dans une case mémoire
	 * - visitVarInsn (54) : assigne une valeur à la variable donnée
	 * - visitLabel : passe à une autre instruction
	 * 
	 * @see org.objectweb.asm.MethodVisitor#visitAnnotation(java.lang.String, boolean)
	 */
	
	public Node getGraph(){
		return this.graph;
	}
	
	//LinkedList<Node> next = new LinkedList<Node>();
	
	public void visitLabel(Label arg0) {
		
		// If node allready exists, don't duplicate
		Node existingNode = this.graph.findNode(getLabelId(arg0));
		
		if(existingNode == null){
			existingNode = new Instruction(getLabelId(arg0), "");
		}
		else {
			//System.out.println("exist "+arg0);
		}
		
		// For conditions don't link if end with else block
		if(linkLast) {
			//System.out.println("from "+currentNode+" to "+existingNode);
			Arc nextArc = new Arc("", existingNode);
			this.currentNode.addArc(nextArc);
		}
		else {
			linkLast = true;
		}
		
		this.currentNode = existingNode;

		System.out.println("\nvisitLabel: " + arg0 + " - currentNode: " + currentNode.getName());
		//next.add(existingNode);
	}
	
	public void visitJumpInsn(int arg0, Label arg1) {		
		
		// Goto instruction : don't link condition end 
		// with else block
		if(arg0 == 167){
			this.linkLast = /*!this.lastCondition*/ false;
			this.lastCondition = false;
		}
		
		// Create else node to use it later
		Node otherwise = this.graph.findNode(getLabelId(arg1));
		if(otherwise == null){
			otherwise = new Instruction(getLabelId(arg1), "");
		}

		Arc arcCond = new Arc("", otherwise);
		this.currentNode.addArc(arcCond);
		//System.out.println("from "+currentNode+" to "+otherwise);
		
		this.lastCondition = true;
		System.out.println("visitJumpInsn: " + arg0 + " # " + arg1 + " - create: " + otherwise.getName());
	}
	
	private int getLabelId(Label label){
		String sLabel = label.toString();
		return Integer.parseInt(sLabel.substring(1, sLabel.length()));
	}
	
	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
		System.out.println("visitAnnotation " + arg0 + " # " + arg1);
		return null;
	}

	public AnnotationVisitor visitAnnotationDefault() {
		System.out.println("visitAnnotationDefault");
		return null;
	}

	public void visitAttribute(Attribute arg0) {
		System.out.println("visitAttribute " + arg0);
	}

	public void visitCode() {
		System.out.println("visitCode");
	}

	public void visitEnd() {
		System.out.println("visitEnd");
	}

	public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3) {
		System.out.println("visitFieldInsn: " + arg0 + " # " + arg1 + " # " + arg2 + " # " + arg3);
	}

	public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3,
			Object[] arg4) {
		System.out.println("visitFrame: " + arg0 + " # " + arg1 + " # " + arg2 + " # " + arg3);
	}

	public void visitIincInsn(int arg0, int arg1) {
		System.out.println("visitIincInsn: " + arg0 + " # " + arg1);
	}

	public void visitInsn(int arg0) {
		System.out.println("visitInsn: " + arg0);
	}

	public void visitIntInsn(int arg0, int arg1) {
		System.out.println("visitIntInsn: " + arg0 + " # " + arg1);
	}

	public void visitLdcInsn(Object arg0) {
		System.out.println("visitLdcInsn: " + arg0);
	}

	public void visitLineNumber(int arg0, Label arg1) {
		System.out.println("visitLineNumber: " + arg0 + " # " + arg1);
	}

	public void visitLocalVariable(String arg0, String arg1, String arg2,
			Label arg3, Label arg4, int arg5) {
		System.out.println("visitLocalVariable: " + arg0 + " # " + arg1 + " # " + arg2 + " # " + arg3 + " # " + arg4 + " # " + arg5);
	}

	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		System.out.println("visitLookupSwitchInsn: " + arg0 + " # " + arg1 + " # " + arg2);
	}

	public void visitMaxs(int arg0, int arg1) {
		System.out.println("visitMaxs: " + arg0 + " # " + arg1);
	}

	public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3) {
		System.out.println("visitMethodInsn: " + arg0 + " # " + arg1 + " # " + arg2 + " # " + arg3);
	}

	public void visitMultiANewArrayInsn(String arg0, int arg1) {
		System.out.println("visitMultiANewArrayInsn: " + arg0 + " # " + arg1);
	}

	public AnnotationVisitor visitParameterAnnotation(int arg0, String arg1,
			boolean arg2) {
		System.out.println("visitParameterAnnotation: " + arg0 + " # " + arg1 + " # " + arg2);
		return null;
	}

	public void visitTableSwitchInsn(int arg0, int arg1, Label arg2,
			Label[] arg3) {
		System.out.println("visitTableSwitchInsn: " + arg0 + " # " + arg1 + " # " + arg2);
	}

	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2,
			String arg3) {
		System.out.println("visitTryCatchBlock: " + arg0 + " # " + arg1 + " # " + arg2 + " # " + arg3);		
	}

	public void visitTypeInsn(int arg0, String arg1) {
		System.out.println("visitTypeInsn: " + arg0 + " # " + arg1);		
	}

	public void visitVarInsn(int arg0, int arg1) {
		System.out.println("visitVarInsn: " + arg0 + " # " + arg1);				
	}
}