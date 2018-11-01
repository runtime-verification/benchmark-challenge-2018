package edu.ltl.wallin.generator;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;

import edu.ltl.wallin.lTL.BinaryExpr;
import edu.ltl.wallin.lTL.Formula;
import edu.ltl.wallin.lTL.IdFormula;
import edu.ltl.wallin.lTL.LTLFactory;
import edu.ltl.wallin.lTL.UnaryExpr;

public class PerformTranslates {
	
	private static int NUM_VARS;
	private static int NUM_TIME_STEPS;
	
	private static int getUpperBound(Formula f) {
		if(f instanceof BinaryExpr) {
			return ((BinaryExpr)f).getUpperBound();
		} else if(f instanceof UnaryExpr) {
			return ((UnaryExpr)f).getUpperBound();
		} else {
			EObject cur = f.eContainer();
			while(cur != null) {
				if(PerformTransforms.isTemporalNode((Formula) cur)) return getUpperBound((Formula) cur);
				cur = cur.eContainer();
			}
			return 0;
		}		
	}
	
	
	private static int getLowerBound(Formula f) {
		if(f instanceof BinaryExpr) {
			return ((BinaryExpr)f).getLowerBound();
		} else if(f instanceof UnaryExpr) {
			return ((UnaryExpr)f).getLowerBound();
		} else {
			EObject cur = f.eContainer();
			while(cur != null) {
				if(PerformTransforms.isTemporalNode((Formula) cur)) return getLowerBound((Formula) cur);
				cur = cur.eContainer();
			}
			return 0;
		}		
	}
	
	private static void translateProp(Formula f) {
		TreeIterator<EObject> treeContents = f.eAllContents();
		Formula cur = f;
		do{
			
			if(cur instanceof IdFormula) {
				IdFormula curChildCast = (IdFormula) cur;
				curChildCast.setVarName(curChildCast.getVarName() + Integer.toString(0));
			}
			
			if(PerformTransforms.getOp(cur).equals("U")) {
				
			}else if(PerformTransforms.getOp(cur).equals("F") || PerformTransforms.getOp(cur).equals("G")) {
				UnaryExpr curCast = (UnaryExpr) cur;
				int lowerBound = getLowerBound(curCast);
				int upperBound = getUpperBound(curCast);
				String newOp = (curCast.getOp().equals("G")) ? "&" : "|";
				ArrayList<Formula> newChildren = new ArrayList<>();
				int i = 0;
				for(; i <= upperBound - lowerBound; i++) {
					newChildren.add(EcoreUtil2.copy(curCast.getExpr()));
					TreeIterator<EObject> iterateChildren = newChildren.get(i).eAllContents();
					EObject curChild = newChildren.get(i);
					do{
						if(curChild instanceof IdFormula) {
							IdFormula curChildCast = (IdFormula) curChild;
							curChildCast.setVarName(curChildCast.getVarName() + Integer.toString(i + lowerBound));
						}
						curChild = (iterateChildren.hasNext()) ? iterateChildren.next() : null;
					}while(curChild != null);
				}
				Formula newParent = LTLFactory.eINSTANCE.createFormula();
				if(newChildren.size() > 1) {
					BinaryExpr newChildExpr = PerformTransforms.composeBinaryExpr(newChildren, newOp);
					newParent = newChildExpr;
				}else {
					UnaryExpr newChildExpr = LTLFactory.eINSTANCE.createUnaryExpr();
					newChildExpr.setExpr(newChildren.get(0));
					newParent = newChildExpr;
				}
				EcoreUtil2.replace(cur, newParent);
			}
			cur = treeContents.hasNext() ? (Formula) treeContents.next() : null;
		}while(cur != null && (cur == f || cur.eContainer() == f));
	}
	
	private static void translateVars(Formula f) {
		TreeIterator<EObject> allFormula = f.eAllContents();
		EObject cur;
		while(allFormula.hasNext()) {
			cur = allFormula.next();
			if(cur instanceof IdFormula) {
				int letter = ((int) ((IdFormula)cur).getVarName().charAt(0)) - ((int) 'a');
				int time_step = Integer.parseInt(((IdFormula)cur).getVarName().substring(1));
				((IdFormula)cur).setVarName(Integer.toString(letter * NUM_TIME_STEPS + time_step + 1));
			}
		}
	}
	
	private static int numVars(Formula f) {
		HashSet<String> vars = new HashSet<String>();
		TreeIterator<EObject> treeContents = f.eAllContents();
		EObject cur = f;
		do {
			if(cur instanceof IdFormula) vars.add(((IdFormula)cur).getVarName());
			if(treeContents.hasNext()) cur = treeContents.next();
			else cur = null;
		}while(cur != null);
		return vars.size();
	}
	
	public static void translateFormula(Resource r, int trace_length) {
		Formula root = (Formula) EcoreUtil2.getRootContainer(r.getContents().get(0));
		NUM_VARS = numVars(root);
		NUM_TIME_STEPS = trace_length;
		
		PerformTransforms.debugPrettyPrinter(root);
		translateProp(root);
		root = (Formula) EcoreUtil2.getRootContainer(r.getContents().get(0));
		PerformTransforms.debugPrettyPrinter(root);
		translateVars(root);
		root = (Formula) EcoreUtil2.getRootContainer(r.getContents().get(0));
		PerformTransforms.debugPrettyPrinter(root);
	}
	
}
