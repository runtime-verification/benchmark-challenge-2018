package edu.ltl.wallin.output;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import edu.ltl.wallin.generator.ComputeDeadlines;
import edu.ltl.wallin.generator.LTLGenerator;
import edu.ltl.wallin.generator.PerformTransforms;
import edu.ltl.wallin.lTL.BinaryExpr;
import edu.ltl.wallin.lTL.Formula;
import edu.ltl.wallin.lTL.IdFormula;
import edu.ltl.wallin.lTL.UnaryExpr;

public class SAT_Output {
	
	private static StringBuffer sb;
	private static final String BCZCHAFF_LOCATION = "/Users/Josh/Desktop/BenchmarkGenerator/bczchaff";
	private static final String Z3 = "z3";
	
	private static void satPrinterHelper(Formula f) {
		if(f instanceof UnaryExpr) {
			UnaryExpr castUnary = (UnaryExpr) f;
			sb.append("(");
			if(!castUnary.getOp().equals("-")) sb.append(castUnary.getOp());
			if(castUnary.getOp().equals("-")) sb.append("not ");
			if(!castUnary.getOp().equals("-")) sb.append("[" + castUnary.getLowerBound() + "," + castUnary.getUpperBound() + "] ");
			if(!castUnary.eContents().isEmpty()) {
				satPrinterHelper((Formula) castUnary.getExpr());
			}
			sb.append(")");
		} else if(f instanceof BinaryExpr) {
			BinaryExpr castBinary = (BinaryExpr) f;
			sb.append("(" + (castBinary.getOp().equals("&") ? "and " : "or "));
			//sb.append(" (");
			satPrinterHelper(castBinary.getLeft());
			sb.append(" ");
			//sb.append(") ");
			satPrinterHelper(castBinary.getRight());
			sb.append(")");
		} else if (f instanceof IdFormula) {
			sb.append(((IdFormula)f).getVarName() + ((IdFormula)f).getLowerBound());
		}
	}
	
	static String satPrinter(Formula f) {
		sb = new StringBuffer();
		satPrinterHelper(f);
		
		return sb.toString();
	}
	
	private static int numVars(Formula f, int trace_length) {
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
	
	private static int maxDepth(Formula f) {
		int maxDepth = 0;
		String varPattern = "[a-zA-Z]";
		TreeIterator<EObject> treeContents = f.eAllContents();
		EObject cur = f;
		do {
			if(cur instanceof IdFormula) {
				if(((IdFormula)cur).getUpperBound() > maxDepth) {
					maxDepth = ((IdFormula)cur).getUpperBound();
				}
			}
			if(treeContents.hasNext()) cur = treeContents.next();
			else cur = null;
		}while(cur != null);
		return maxDepth;
	}
	
	private static void incrementFormula(Formula f) {
		TreeIterator<EObject> treeContents = f.eAllContents();
		EObject cur = f;
		do {
			if(cur instanceof IdFormula) {
				((IdFormula) cur).setLowerBound(((IdFormula) cur).getLowerBound() + 1);
				((IdFormula) cur).setUpperBound(((IdFormula) cur).getUpperBound() + 1);
			}
			if(treeContents.hasNext()) cur = treeContents.next();
			else cur = null;
		}while(cur != null);
		
	}
	
	public static String writeForSMT(Formula f, String output_filename, int trace_length) {
		StringBuffer Sat_buffer = new StringBuffer();

		for(String s : ComputeDeadlines.numVars(f)) {
			if(s.equalsIgnoreCase("initial")) continue;
			for(int j = 0; j <= trace_length + maxDepth(f); j++) {
				Sat_buffer.append("(declare-fun " + (s) + Integer.toString(j) + "() (Bool))\n");
			}
		}
		
		for(int j = 0; j <=trace_length + maxDepth(f); j++) {
			Sat_buffer.append("(declare-fun INITIAL" + j +"() (Bool))\n");
		}
	
		int t = 0;
		Sat_buffer.append("(define-fun O_t" + t + "() Bool" + "(and " + satPrinter(f) + " INITIAL0)" + ")\n");
		Sat_buffer.append("(assert O_t" + t + ")\n");
		incrementFormula(f);
		for(t = 1; t < trace_length; t++) {
			//Sat_buffer.append("(declare-fun t" + t + "() (Bool))\n");
			Sat_buffer.append("(define-fun O_t" + t + "() Bool" + satPrinter(f) + ")\n");
			Sat_buffer.append("(assert-soft O_t" + t + ")\n");
			Sat_buffer.append("(assert (not INITIAL" + t + "))");
			//Sat_buffer.append("(assert-soft (and t" + t + " (and (or (not" + satPrinter(f) + ") t" + t + ")" + " (or " + satPrinter(f) + " (not t" + t + ")))" + "))\n");
			//Sat_buffer.append("(assert-soft (and (or (not t" + t + ") " +   satPrinter(f) + ") t" + t + "))\n");
			//increment formula by 1
			incrementFormula(f);
		}
		Sat_buffer.append("(set-option :opt.priority pareto)\n" + 
				"(check-sat)\n(get-model)\n");
		Sat_buffer.append("(echo \" \")\n");
		for(t = 0; t <trace_length; t++) {
			Sat_buffer.append("(eval O_t" + t + ")\n");
			Sat_buffer.append("(echo \" \")\n");
		}
		String output = Sat_buffer.toString();
		
		try {
			BufferedWriter output_writer = new BufferedWriter(new FileWriter(output_filename + ".smt"));
			output_writer.write(output);
			output_writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

	public static String callGetBczchaff(String input_filename) {
		String response = "";
		String temp = "";
		try {
			Process run_bczchaff = Runtime.getRuntime().exec(BCZCHAFF_LOCATION + " " + input_filename);
			BufferedReader response_reader = new BufferedReader(new InputStreamReader(run_bczchaff.getInputStream()));
			while((temp = response_reader.readLine()) != null) {
				response += temp;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static String callGetZ3(String input_filename) {
		String response = "";
		String temp = "";
		try {
			Process run_z3 = Runtime.getRuntime().exec(Z3 + " " + input_filename);
			BufferedReader response_reader = new BufferedReader(new InputStreamReader(run_z3.getInputStream()));
			while((temp = response_reader.readLine()) != null) {
				response += temp;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static String processBczchaffResponse(String bczchaff_result, String output_filename, HashSet<String> var_set, int trace_length) {
		if(bczchaff_result.contains("Unsatisfiable")) return "UNSATISFIABLE -- CANNOT GENERATE TRACE FOR INPUT FORMULAS";

		String trace = "";
		HashMap<String, HashMap<Integer, Boolean>> variableValues = new HashMap<String, HashMap<Integer, Boolean>>();
				
		//Set all variables to false before reading in actual values
		for(String var : var_set) {
			HashMap<Integer, Boolean> varMap = new HashMap<Integer, Boolean>();
			for(int j = 0; j < trace_length; j++) {
				varMap.put(j, false);
			}
			variableValues.put(var, varMap);
		}
		

		//Read bczchaff_result for var values
		String[] results = bczchaff_result.split(" ");
		for(String s : results) {
			if(s.equals("Satisfiable")) break;
			
			boolean isTrue = !(s.substring(0,1).equals("!"));
			String curVar = (isTrue) ? s.substring(0,1) : s.substring(1,2);
			int curVarNumber = (isTrue) ? Integer.parseInt(s.substring(1)) : Integer.parseInt(s.substring(2));
			
			variableValues.get(curVar).put(curVarNumber, isTrue);
		}
		
		
			
		//Add variable labels at top
		trace += "TIME, ";
		for(String s : var_set) {
			trace += (s) + ", ";
		}
		trace += "\n";
		//Add variable values
		for(int t = 0; t < trace_length; t++) {
			
			trace += t + ", ";
			
			for(String s : var_set) {
				trace += variableValues.get(s).get(t) + ", ";
			}
			
			trace += "\n";
		}
		trace += "\n";
		
		BufferedWriter trace_output;
		try {
			trace_output = new BufferedWriter(new FileWriter(output_filename + "_trace_output.csv"));
			trace_output.write(trace);
			trace_output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return trace;
	}
	
	public static String processSMTResponse(String smt_result, String output_filename, HashSet<String> var_set, int trace_length) {
		String trace = "";
		HashMap<String, HashMap<Integer, Boolean>> variableValues = new HashMap<String, HashMap<Integer, Boolean>>();
			
		var_set.add("O_t");
		var_set.add("INITIAL");
		//Set all variables to false before reading in actual values
		for(String var : var_set) {
			HashMap<Integer, Boolean> varMap = new HashMap<Integer, Boolean>();
			for(int j = 0; j < trace_length; j++) {
				varMap.put(j, false);
			}
			variableValues.put(var, varMap);
		}
		

//		for(int i = 0; i < trace_length; i++) {
//			HashMap<Integer, Boolean> varMap = new HashMap<Integer, Boolean>();
//			varMap.put(i, false);
//			variableValues.put("O_t", varMap);
//		}
//		for(int i = 0; i < trace_length; i++) {
//			HashMap<Integer, Boolean> varMap = new HashMap<Integer, Boolean>();
//			varMap.put(i, false);
//			variableValues.put("INITIAL", varMap);
//		}
		
		if(!smt_result.contains("unsat")) {
		
			//Read result for var values
			String[] results = smt_result.split(" ");
	
	
			String s;
			int i = 0;
	
			for(i = 0; i < results.length - trace_length; i++) {
				s = results[i];
				if(s.contains("define-fun")) {
					i++;
					String  var_name = results[i]; //Now is variable name;
					i+=6;
					s = results[i];
					boolean isTrue = s.contains("true");
					variableValues.get(var_name.replaceAll("[0-9]", "")).put(Integer.parseInt(var_name.replaceAll("[^0-9]", "")), isTrue);
				}
			}
			
			for(int t = i; t < i + trace_length; t++) {
				variableValues.get("O_t").put((t-i), results[t].contains("true"));
			}
		}
		

		//Add variable labels at top
		trace += "TIME, ";
		for(String r : var_set) {
			if(r.equalsIgnoreCase("initial")) continue;
			trace += (r) + ", ";
		}
		trace += "\n";
		//Add variable values
		for(int t = 0; t < trace_length; t++) {
			
			trace += t + ", ";
			
			for(String v : var_set) {
				if(v.equalsIgnoreCase("initial")) continue;
				trace += variableValues.get(v).get(t) + ", ";
			}
			
			trace += "\n";
		}
		trace += "\n";
		
		BufferedWriter trace_output;
		try {
			trace_output = new BufferedWriter(new FileWriter(output_filename + "_trace_output.csv"));
			trace_output.write(trace);
			trace_output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return trace;
	}
	
}
