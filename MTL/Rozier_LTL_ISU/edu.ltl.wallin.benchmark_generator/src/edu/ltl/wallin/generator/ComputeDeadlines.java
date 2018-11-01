package edu.ltl.wallin.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;

import edu.ltl.wallin.lTL.BinaryExpr;
import edu.ltl.wallin.lTL.Formula;
import edu.ltl.wallin.lTL.IdFormula;
import edu.ltl.wallin.lTL.LTLFactory;
import edu.ltl.wallin.lTL.UnaryExpr;
import edu.ltl.wallin.generator.Operators;

public class ComputeDeadlines {
	
	static int TRACE_LENGTH;

	private static IdFormula composeIdFormula(String argument,int lowerBound, int upperBound) {
		IdFormula newExpr = LTLFactory.eINSTANCE.createIdFormula();
		newExpr.setVarName(argument);
		newExpr.setLowerBound(lowerBound);
		newExpr.setUpperBound(upperBound);
		return newExpr;
	}
	
	private static boolean isTemporalNode(Formula f) {
		return PerformTransforms.getOp(f).equals(Operators.GLOBALLY) || PerformTransforms.getOp(f).equals(Operators.EVENTUALLY) || PerformTransforms.getOp(f).equals(Operators.UNTIL);
	}
	
	private static Formula eliminateOperator(Formula f) {
		switch(PerformTransforms.getOp(f)) {
			case Operators.GLOBALLY:
			case Operators.EVENTUALLY:
				String newOp = (PerformTransforms.getOp(f).equals("G")) ? "&" : "|";
				ArrayList<Formula> newChildren = new ArrayList<Formula>();
				UnaryExpr f_cast_unary = (UnaryExpr) f;
				for(int i = f_cast_unary.getLowerBound(); i <= f_cast_unary.getUpperBound(); i++) {
					Formula newChild = EcoreUtil2.copy(f_cast_unary.getExpr());
					TreeIterator<EObject> childContents = newChild.eAllContents();
					EObject cur = newChild;
					IdFormula replacement_child;
					while(cur != null) {	
						if(cur instanceof IdFormula) {
							//replacement_child = composeIdFormula(((IdFormula) cur).getVarName(), ((IdFormula) cur).getLowerBound() + i, ((IdFormula) cur).getUpperBound() + i);
							//EcoreUtil2.replace(cur, replacement_child);
							((IdFormula) cur).setLowerBound(((IdFormula) cur).getLowerBound() + i);
							((IdFormula) cur).setUpperBound(((IdFormula) cur).getUpperBound() + i);
						}
						cur = childContents.hasNext() ? childContents.next() : null;
					}
					newChildren.add(newChild);
				}
				Formula newFormula;
				if(newChildren.size() > 1) {
					newFormula = PerformTransforms.composeBinaryExpr(newChildren, newOp);
					EcoreUtil2.replace(f, newFormula);
				}else {
					newFormula = newChildren.get(0);
					EcoreUtil2.replace(f, newFormula);
				}
				return newFormula;
			case Operators.UNTIL:
				ArrayList<Formula> newDisjunctArguments = new ArrayList<Formula>();
				BinaryExpr f_cast_binary = (BinaryExpr) f;
				Formula left_child = f_cast_binary.getLeft();
				Formula right_child = f_cast_binary.getRight();
				for(int i = f_cast_binary.getLowerBound(); i <= f_cast_binary.getUpperBound(); i++) {
					ArrayList<Formula> newConjunctArguments = new ArrayList<Formula>();
					
					//Left Side
					for(int j = f_cast_binary.getLowerBound(); j < i; j++) {
						Formula newLeftChild = EcoreUtil2.copy(left_child);
						TreeIterator<EObject> childContents = newLeftChild.eAllContents();
						EObject cur = newLeftChild;
						IdFormula replacement_child;
						while(cur != null) {
							if(cur instanceof IdFormula) {
								//replacement_child = composeIdFormula(((IdFormula)cur).getVarName(), ((IdFormula)cur).getLowerBound() + j, ((IdFormula)cur).getUpperBound() + j);
								//EcoreUtil2.replace(cur, replacement_child);
								((IdFormula) cur).setLowerBound(((IdFormula) cur).getLowerBound() + j);
								((IdFormula) cur).setUpperBound(((IdFormula) cur).getUpperBound() + j);
							}
							cur = (childContents.hasNext()) ? childContents.next() : null;
						}
						newConjunctArguments.add(newLeftChild);
					}
					
					//Right Side
					Formula newRightChild = EcoreUtil2.copy(right_child);
					TreeIterator<EObject> childContents = newRightChild.eAllContents();
					EObject cur = newRightChild;
					IdFormula replacement_child;
					while(cur != null) {
						if(cur instanceof IdFormula) {
							//replacement_child = composeIdFormula(((IdFormula) cur).getVarName(), ((IdFormula) cur).getLowerBound() + i, ((IdFormula) cur).getUpperBound() + i);
							//EcoreUtil2.replace(cur, replacement_child);
							((IdFormula) cur).setLowerBound(((IdFormula) cur).getLowerBound() + i);
							((IdFormula) cur).setUpperBound(((IdFormula) cur).getUpperBound() + i);
						}
						cur = (childContents.hasNext()) ? childContents.next() : null;
					}
					newConjunctArguments.add(newRightChild);
					
					Formula newConjunct;
					if(newConjunctArguments.size() > 1) {
						newConjunct = PerformTransforms.composeBinaryExpr(newConjunctArguments, "&");
					}else {
						newConjunct = newConjunctArguments.get(0);
					}
					newDisjunctArguments.add(newConjunct);
				}
				Formula newDisjunct;
				if(newDisjunctArguments.size() > 1) {
					newDisjunct = PerformTransforms.composeBinaryExpr(newDisjunctArguments, "|");
				}else {
					newDisjunct = newDisjunctArguments.get(0);
				}
				EcoreUtil2.replace(f, newDisjunct);
				return newDisjunct;
		}
		return null;
	}
	
	private static boolean containsTemporalOperators(Formula f) {
		TreeIterator<EObject> treeContents = f.eAllContents();
		EObject cur = treeContents.hasNext() ? treeContents.next() : null;
		while(cur != null) {
			if(isTemporalNode((Formula) cur)) return true;
			cur = (treeContents.hasNext()) ? treeContents.next() : null;
		}
		return false;
	}
	
	public static HashSet<String> numVars(Formula f) {
		HashSet<String> vars = new HashSet<String>();
		TreeIterator<EObject> treeContents = f.eAllContents();
		EObject cur = f;
		do {
			if(cur instanceof IdFormula) vars.add(((IdFormula)cur).getVarName());
			if(treeContents.hasNext()) cur = treeContents.next();
			else cur = null;
		}while(cur != null);
		return vars;
	}
	
	private static Formula getTemporalLeaf(Formula f) {
		Formula leaf = null;
		
		Queue<Formula> traversal_queue = new LinkedList<Formula>();
		Stack<Formula> reverseBFS = new Stack<Formula>();
		traversal_queue.add(f);
		
		while(!traversal_queue.isEmpty()) {
			Formula cur = traversal_queue.poll();
			reverseBFS.add(cur);
			if(cur instanceof BinaryExpr) {
				BinaryExpr cur_cast = (BinaryExpr) cur;
				traversal_queue.add(cur_cast.getLeft());
				traversal_queue.add(cur_cast.getRight());
			}else if(cur instanceof UnaryExpr) {
				UnaryExpr cur_cast = (UnaryExpr) cur;
				traversal_queue.add(cur_cast.getExpr());
			}
		}
		
		while(!reverseBFS.isEmpty()) {
			Formula cur = reverseBFS.pop();
			if(isTemporalNode(cur) && !containsTemporalOperators(cur)) {
				leaf = cur;
				return leaf;
			}
		}
		
		return leaf;
	}
	
	public static void checkBounds(Formula f) {
		TreeIterator<EObject> treeContents = f.eAllContents();
		Formula cur = f;
		while(cur != null) {
			if(isTemporalNode(cur)) {
				if(cur instanceof BinaryExpr) {
					BinaryExpr cur_cast = (BinaryExpr) cur;
					if(cur_cast.isEnd()) cur_cast.setUpperBound(TRACE_LENGTH);
				}else if(cur instanceof UnaryExpr) {
					UnaryExpr cur_cast = (UnaryExpr) cur;
					if(cur_cast.isEnd()) cur_cast.setUpperBound(TRACE_LENGTH);
				}
			}
			cur = (treeContents.hasNext()) ? (Formula) treeContents.next() : null;
		}
	}
	
	public static int transformFormula(Resource r, int trace_length, boolean verbose, String output_filename) {
		Formula root = (Formula) r.getContents().get(0);
		int num_vars = numVars(root).size();
		TRACE_LENGTH = trace_length;
		StringBuffer output = new StringBuffer();
		checkBounds(root);
		root = (Formula) r.getContents().get(0);
		if(verbose) System.out.println("INPUT FORMULA: " + PerformTransforms.debugPrettyPrinter(root));
		//Find a nested temporal expression ("a temporal leaf")
		//Expand the leaf
		//Repeat until there aren't any temporal expressions
		while(containsTemporalOperators(root)) {
			Formula leaf = getTemporalLeaf(root);
			eliminateOperator(leaf);
			root = (Formula) r.getContents().get(0);
		}
		
		if(isTemporalNode(root)) {
			root = eliminateOperator(root);
		}
		root = (Formula) r.getContents().get(0);
		if(verbose)System.out.println("OUTPUT FORMULA: " + PerformTransforms.debugPrettyPrinter(root));
		return num_vars;
	}
	
}
