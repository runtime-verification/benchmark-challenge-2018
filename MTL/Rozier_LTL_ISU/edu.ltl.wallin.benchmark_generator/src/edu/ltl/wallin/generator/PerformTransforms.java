package edu.ltl.wallin.generator;

import java.util.ArrayList;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import edu.ltl.wallin.lTL.BinaryExpr;
import edu.ltl.wallin.lTL.Formula;
import edu.ltl.wallin.lTL.IdFormula;
import edu.ltl.wallin.lTL.LTLFactory;
import edu.ltl.wallin.lTL.UnaryExpr;
import edu.ltl.wallin.generator.Operators;

import org.eclipse.xtext.EcoreUtil2;

public class PerformTransforms {
	
	private static StringBuffer sb = new StringBuffer();
	
	private static void debugPrettyPrinterHelper(Formula f) {
		if(f instanceof UnaryExpr) {
			UnaryExpr castUnary = (UnaryExpr) f;
			if(!castUnary.getOp().equals("-")) sb.append(castUnary.getOp());
			if(castUnary.getOp().equals("-")) sb.append("!");
			if(!castUnary.getOp().equals("-")) sb.append("[" + castUnary.getLowerBound() + "," + castUnary.getUpperBound() + "] ");
			sb.append("(");
			if(!castUnary.eContents().isEmpty()) {
				debugPrettyPrinterHelper((Formula) castUnary.getExpr());
			}
			sb.append(")");
		} else if(f instanceof BinaryExpr) {
			BinaryExpr castBinary = (BinaryExpr) f;
			sb.append("(");
			debugPrettyPrinterHelper(castBinary.getLeft());
			sb.append(")");
			sb.append(" " + castBinary.getOp() + " ");
			if(castBinary.getLowerBound() != castBinary.getUpperBound()) {
				sb.append("[" + castBinary.getLowerBound() + "," + castBinary.getUpperBound() + "] ");
			}
			sb.append("(");
			debugPrettyPrinterHelper(castBinary.getRight());
			sb.append(")");
		} else if (f instanceof IdFormula) {
			sb.append(((IdFormula)f).getVarName() + ((IdFormula)f).getLowerBound());
		}
	}
	
	static String debugPrettyPrinter(Formula f) {
		sb = new StringBuffer();
		debugPrettyPrinterHelper(f);
		sb.append(";\n");
		return sb.toString();
	}
	
	static BinaryExpr composeBinaryExpr(ArrayList<Formula> subFormulas, String op) {
		BinaryExpr newFormula = LTLFactory.eINSTANCE.createBinaryExpr();
		newFormula.setOp(op);
		BinaryExpr travFormula = newFormula;
		
		int i = 0;
		travFormula.setLeft(EcoreUtil2.copy(subFormulas.get(i)));
		for(i = 1; i < subFormulas.size() - 1; i++) {
			travFormula.setRight(LTLFactory.eINSTANCE.createBinaryExpr());
			travFormula = (BinaryExpr) travFormula.getRight();
			travFormula.setOp(op);
			travFormula.setLeft(EcoreUtil2.copy(subFormulas.get(i)));
		}
		travFormula.setRight(EcoreUtil2.copy(subFormulas.get(i)));
		
		return newFormula;
	}
	
	static void setTimeBounds(Formula f, int lowerBound, int upperBound) {
		if(f instanceof BinaryExpr) {
			((BinaryExpr) f).setLowerBound(lowerBound);
			((BinaryExpr) f).setUpperBound(upperBound);
		}else if(f instanceof UnaryExpr) {
			((UnaryExpr) f).setLowerBound(lowerBound);
			((UnaryExpr) f).setUpperBound(upperBound);
		}else {
			return;
		}
	}
	
	static int getLowerBound(Formula f) {
		if(f instanceof BinaryExpr) {
			return ((BinaryExpr)f).getLowerBound();
		} else if(f instanceof UnaryExpr) {
			return ((UnaryExpr)f).getLowerBound();
		} else {
			return 0;
		}
	}
	
	static int getUpperBound(Formula f) {
		if(f instanceof BinaryExpr) {
			return ((BinaryExpr)f).getUpperBound();
		} else if(f instanceof UnaryExpr) {
			return ((UnaryExpr)f).getUpperBound();
		} else {
			return 0;
		}
	}
	
	static String getOp(Formula f) {
		if(f instanceof BinaryExpr && ((BinaryExpr)f).getOp() != null) {
			return ((BinaryExpr)f).getOp();
		}else if(f instanceof UnaryExpr && ((UnaryExpr)f).getOp() != null) {
			return ((UnaryExpr)f).getOp();
		} else {
			return " ";
		}
	}
	
	static Boolean isTemporalNode(Formula f) {
		return getOp(f).equals("F") || getOp(f).equals("G") || getOp(f).equals("U");
	}
	
	public static void recursiveTransform(Formula f) {
		
		//If f is a literal, no simplification is necessary; just return
		if(f instanceof IdFormula) return;		
		
		//Otherwise, use the op to decide how to proceed
		switch(getOp(f)) {
			case Operators.NOT:
				recursiveTransform(((UnaryExpr)f).getExpr());
				break;
			case Operators.AND:
			case Operators.OR:
				recursiveTransform(((BinaryExpr)f).getLeft());
				recursiveTransform(((BinaryExpr)f).getRight());
				break;
			case Operators.UNTIL:
				BinaryExpr f_cast = (BinaryExpr) f;
				Formula f_left_child = f_cast.getLeft();
				Formula f_right_child = f_cast.getRight();
				//If both children don't contain temporal operators, just recurse into them
				if(!isTemporalNode(f_left_child) && !isTemporalNode(f_right_child)) {
					recursiveTransform(f_left_child);
					recursiveTransform(f_right_child);
					return;
				}
				//Otherwise, continue and construct new node
				ArrayList<Formula> disjunctChildren = new ArrayList<Formula>();
				//Super disjunct loop
				int curLine = 0;
				ArrayList<Formula> newClauseChildren;
				for(int i = f_cast.getLowerBound(); i <= f_cast.getUpperBound(); i++) {
					//ArrayList used to hold children AT EACH LOOP
					newClauseChildren = new ArrayList<Formula>();
					//Get left side children
					for(int j = 0; j < curLine; j++) {
						Formula newChild = EcoreUtil.copy(f_left_child);
						int newLowerBound = getLowerBound(f_left_child) + i;
						int newUpperBound = getUpperBound(f_left_child) - getLowerBound(f_left_child) + newLowerBound;
						setTimeBounds(newChild, newLowerBound, newUpperBound);
						newClauseChildren.add(newChild);
					}
					
					//Add right side child
					UnaryExpr newChild = LTLFactory.eINSTANCE.createUnaryExpr();
					newChild.setExpr(EcoreUtil.copy(f_right_child));
					int newLowerBound = getLowerBound(f_right_child) + getLowerBound(f_cast) + i;
					int newUpperBound = getUpperBound(f_right_child) - getLowerBound(f_right_child) + newLowerBound;
					setTimeBounds(newChild, newLowerBound, newUpperBound);
					newClauseChildren.add(newChild);
					
					if(newClauseChildren.size() > 1){
						disjunctChildren.add(composeBinaryExpr(newClauseChildren, "&"));
					} else {
						disjunctChildren.add(newChild);
					}
					
					curLine++;
				}
				if(disjunctChildren.size() > 1) {
					BinaryExpr newDisjunct = composeBinaryExpr(disjunctChildren, "|");
					EcoreUtil2.replace(f, newDisjunct);
					recursiveTransform(newDisjunct);
				}else if (disjunctChildren.size() == 1){
					EcoreUtil2.replace(f, disjunctChildren.get(0));
					recursiveTransform(disjunctChildren.get(0));
				}
				return;
			case Operators.EVENTUALLY:
			case Operators.GLOBALLY:	
				UnaryExpr uexpr = (UnaryExpr) f;
				Formula originalChild = uexpr.getExpr();
				
				if(!isTemporalNode(originalChild)) {
					recursiveTransform(originalChild);
					return;
				}
				
				//If it is a 'globally formula' or a 'eventually formula', then simplify
				String newOp = (uexpr.getOp().equals("G")) ? "&" : "|";
				
				ArrayList<Formula> newChildren = new ArrayList<>();
				for(int i = 0; i <= uexpr.getUpperBound() - uexpr.getLowerBound(); i++) {
					int newLowerBound = uexpr.getLowerBound() + getLowerBound(originalChild) + i;
					int newUpperBound = getUpperBound(originalChild) - getLowerBound(originalChild) + newLowerBound;
					Formula newChild = EcoreUtil2.copy(originalChild);
					setTimeBounds(newChild, newLowerBound, newUpperBound);
					newChildren.add(newChild);
				}
				//Create a new formula to replace the original parent
				Formula newParent = LTLFactory.eINSTANCE.createFormula();
				//Case 1: More than one child: need to create a binary expression to hold them
				if(newChildren.size() > 1) {
					newParent = composeBinaryExpr(newChildren, newOp);
				}//Case 2: Single child: can just throw child into new UnaryExpr
				else if(!newChildren.isEmpty()) {
					newParent = newChildren.get(0);
				}else if(newChildren.isEmpty()) return;
				//Replace the (now expanded) temporal parent with the expanded children
				EcoreUtil2.replace(uexpr, newParent);
				//Start from the new parent and continue expanding
				recursiveTransform(newParent);
				return;		
		}

	}
	
	public static void transformFormula(Resource r) {
			Formula root = (Formula) EcoreUtil2.getRootContainer(r.getContents().get(0));
			System.out.print("[Transform] Input: ");
			debugPrettyPrinter(root);
			recursiveTransform(root);
			root = (Formula) EcoreUtil2.getRootContainer(r.getContents().get(0));
			System.out.print("[Transform] Output: ");
			debugPrettyPrinter(root);
	}
	
}

