/*
 * generated by Xtext 2.14.0
 */
grammar InternalLTL;

options {
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package edu.ltl.wallin.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package edu.ltl.wallin.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import edu.ltl.wallin.services.LTLGrammarAccess;

}

@parser::members {

 	private LTLGrammarAccess grammarAccess;

    public InternalLTLParser(TokenStream input, LTLGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "Formula";
   	}

   	@Override
   	protected LTLGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleFormula
entryRuleFormula returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFormulaRule()); }
	iv_ruleFormula=ruleFormula
	{ $current=$iv_ruleFormula.current; }
	EOF;

// Rule Formula
ruleFormula returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getFormulaAccess().getUntilFormulaParserRuleCall());
	}
	this_UntilFormula_0=ruleUntilFormula
	{
		$current = $this_UntilFormula_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleUntilFormula
entryRuleUntilFormula returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getUntilFormulaRule()); }
	iv_ruleUntilFormula=ruleUntilFormula
	{ $current=$iv_ruleUntilFormula.current; }
	EOF;

// Rule UntilFormula
ruleUntilFormula returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getUntilFormulaAccess().getConnectiveFormulaParserRuleCall_0());
		}
		this_ConnectiveFormula_0=ruleConnectiveFormula
		{
			$current = $this_ConnectiveFormula_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				((
					(
					)
					(
						(
							'U'
						)
					)
					'['
					(
						(
							RULE_INT
						)
					)?
					','
					(
						(
							'end'
						)
					)?
					(
						(
							RULE_INT
						)
					)?
					']'
				)
				)=>
				(
					(
						{
							$current = forceCreateModelElementAndSet(
								grammarAccess.getUntilFormulaAccess().getBinaryExprLeftAction_1_0_0_0(),
								$current);
						}
					)
					(
						(
							lv_op_2_0='U'
							{
								newLeafNode(lv_op_2_0, grammarAccess.getUntilFormulaAccess().getOpUKeyword_1_0_0_1_0());
							}
							{
								if ($current==null) {
									$current = createModelElement(grammarAccess.getUntilFormulaRule());
								}
								setWithLastConsumed($current, "op", lv_op_2_0, "U");
							}
						)
					)
					otherlv_3='['
					{
						newLeafNode(otherlv_3, grammarAccess.getUntilFormulaAccess().getLeftSquareBracketKeyword_1_0_0_2());
					}
					(
						(
							lv_lowerBound_4_0=RULE_INT
							{
								newLeafNode(lv_lowerBound_4_0, grammarAccess.getUntilFormulaAccess().getLowerBoundINTTerminalRuleCall_1_0_0_3_0());
							}
							{
								if ($current==null) {
									$current = createModelElement(grammarAccess.getUntilFormulaRule());
								}
								setWithLastConsumed(
									$current,
									"lowerBound",
									lv_lowerBound_4_0,
									"org.eclipse.xtext.common.Terminals.INT");
							}
						)
					)?
					otherlv_5=','
					{
						newLeafNode(otherlv_5, grammarAccess.getUntilFormulaAccess().getCommaKeyword_1_0_0_4());
					}
					(
						(
							lv_end_6_0='end'
							{
								newLeafNode(lv_end_6_0, grammarAccess.getUntilFormulaAccess().getEndEndKeyword_1_0_0_5_0());
							}
							{
								if ($current==null) {
									$current = createModelElement(grammarAccess.getUntilFormulaRule());
								}
								setWithLastConsumed($current, "end", true, "end");
							}
						)
					)?
					(
						(
							lv_upperBound_7_0=RULE_INT
							{
								newLeafNode(lv_upperBound_7_0, grammarAccess.getUntilFormulaAccess().getUpperBoundINTTerminalRuleCall_1_0_0_6_0());
							}
							{
								if ($current==null) {
									$current = createModelElement(grammarAccess.getUntilFormulaRule());
								}
								setWithLastConsumed(
									$current,
									"upperBound",
									lv_upperBound_7_0,
									"org.eclipse.xtext.common.Terminals.INT");
							}
						)
					)?
					otherlv_8=']'
					{
						newLeafNode(otherlv_8, grammarAccess.getUntilFormulaAccess().getRightSquareBracketKeyword_1_0_0_7());
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getUntilFormulaAccess().getRightConnectiveFormulaParserRuleCall_1_1_0());
					}
					lv_right_9_0=ruleConnectiveFormula
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getUntilFormulaRule());
						}
						set(
							$current,
							"right",
							lv_right_9_0,
							"edu.ltl.wallin.LTL.ConnectiveFormula");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleConnectiveFormula
entryRuleConnectiveFormula returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getConnectiveFormulaRule()); }
	iv_ruleConnectiveFormula=ruleConnectiveFormula
	{ $current=$iv_ruleConnectiveFormula.current; }
	EOF;

// Rule ConnectiveFormula
ruleConnectiveFormula returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getConnectiveFormulaAccess().getUnaryExprParserRuleCall_0());
		}
		this_UnaryExpr_0=ruleUnaryExpr
		{
			$current = $this_UnaryExpr_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				((
					(
					)
					(
						(
							(
								'&'
								    |
								'|'
							)
						)
					)
				)
				)=>
				(
					(
						{
							$current = forceCreateModelElementAndSet(
								grammarAccess.getConnectiveFormulaAccess().getBinaryExprLeftAction_1_0_0_0(),
								$current);
						}
					)
					(
						(
							(
								lv_op_2_1='&'
								{
									newLeafNode(lv_op_2_1, grammarAccess.getConnectiveFormulaAccess().getOpAmpersandKeyword_1_0_0_1_0_0());
								}
								{
									if ($current==null) {
										$current = createModelElement(grammarAccess.getConnectiveFormulaRule());
									}
									setWithLastConsumed($current, "op", lv_op_2_1, null);
								}
								    |
								lv_op_2_2='|'
								{
									newLeafNode(lv_op_2_2, grammarAccess.getConnectiveFormulaAccess().getOpVerticalLineKeyword_1_0_0_1_0_1());
								}
								{
									if ($current==null) {
										$current = createModelElement(grammarAccess.getConnectiveFormulaRule());
									}
									setWithLastConsumed($current, "op", lv_op_2_2, null);
								}
							)
						)
					)
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getConnectiveFormulaAccess().getRightUnaryExprParserRuleCall_1_1_0());
					}
					lv_right_3_0=ruleUnaryExpr
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getConnectiveFormulaRule());
						}
						set(
							$current,
							"right",
							lv_right_3_0,
							"edu.ltl.wallin.LTL.UnaryExpr");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleUnaryExpr
entryRuleUnaryExpr returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getUnaryExprRule()); }
	iv_ruleUnaryExpr=ruleUnaryExpr
	{ $current=$iv_ruleUnaryExpr.current; }
	EOF;

// Rule UnaryExpr
ruleUnaryExpr returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getUnaryExprAccess().getUnaryExprAction_0_0(),
						$current);
				}
			)
			(
				(
					(
						lv_op_1_1='F'
						{
							newLeafNode(lv_op_1_1, grammarAccess.getUnaryExprAccess().getOpFKeyword_0_1_0_0());
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getUnaryExprRule());
							}
							setWithLastConsumed($current, "op", lv_op_1_1, null);
						}
						    |
						lv_op_1_2='G'
						{
							newLeafNode(lv_op_1_2, grammarAccess.getUnaryExprAccess().getOpGKeyword_0_1_0_1());
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getUnaryExprRule());
							}
							setWithLastConsumed($current, "op", lv_op_1_2, null);
						}
					)
				)
			)
			otherlv_2='['
			{
				newLeafNode(otherlv_2, grammarAccess.getUnaryExprAccess().getLeftSquareBracketKeyword_0_2());
			}
			(
				(
					lv_lowerBound_3_0=RULE_INT
					{
						newLeafNode(lv_lowerBound_3_0, grammarAccess.getUnaryExprAccess().getLowerBoundINTTerminalRuleCall_0_3_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getUnaryExprRule());
						}
						setWithLastConsumed(
							$current,
							"lowerBound",
							lv_lowerBound_3_0,
							"org.eclipse.xtext.common.Terminals.INT");
					}
				)
			)?
			otherlv_4=','
			{
				newLeafNode(otherlv_4, grammarAccess.getUnaryExprAccess().getCommaKeyword_0_4());
			}
			(
				(
					lv_end_5_0='end'
					{
						newLeafNode(lv_end_5_0, grammarAccess.getUnaryExprAccess().getEndEndKeyword_0_5_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getUnaryExprRule());
						}
						setWithLastConsumed($current, "end", true, "end");
					}
				)
			)?
			(
				(
					lv_upperBound_6_0=RULE_INT
					{
						newLeafNode(lv_upperBound_6_0, grammarAccess.getUnaryExprAccess().getUpperBoundINTTerminalRuleCall_0_6_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getUnaryExprRule());
						}
						setWithLastConsumed(
							$current,
							"upperBound",
							lv_upperBound_6_0,
							"org.eclipse.xtext.common.Terminals.INT");
					}
				)
			)?
			otherlv_7=']'
			{
				newLeafNode(otherlv_7, grammarAccess.getUnaryExprAccess().getRightSquareBracketKeyword_0_7());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getUnaryExprAccess().getExprUnaryExprParserRuleCall_0_8_0());
					}
					lv_expr_8_0=ruleUnaryExpr
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getUnaryExprRule());
						}
						set(
							$current,
							"expr",
							lv_expr_8_0,
							"edu.ltl.wallin.LTL.UnaryExpr");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getUnaryExprAccess().getUnaryExprAction_1_0(),
						$current);
				}
			)
			(
				(
					lv_op_10_0='-'
					{
						newLeafNode(lv_op_10_0, grammarAccess.getUnaryExprAccess().getOpHyphenMinusKeyword_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getUnaryExprRule());
						}
						setWithLastConsumed($current, "op", lv_op_10_0, "-");
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getUnaryExprAccess().getExprUnaryExprParserRuleCall_1_2_0());
					}
					lv_expr_11_0=ruleUnaryExpr
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getUnaryExprRule());
						}
						set(
							$current,
							"expr",
							lv_expr_11_0,
							"edu.ltl.wallin.LTL.UnaryExpr");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
		    |
		{
			newCompositeNode(grammarAccess.getUnaryExprAccess().getLiteralParserRuleCall_2());
		}
		this_Literal_12=ruleLiteral
		{
			$current = $this_Literal_12.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleLiteral
entryRuleLiteral returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getLiteralRule()); }
	iv_ruleLiteral=ruleLiteral
	{ $current=$iv_ruleLiteral.current; }
	EOF;

// Rule Literal
ruleLiteral returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getLiteralAccess().getIdFormulaAction_0_0(),
						$current);
				}
			)
			(
				(
					lv_varName_1_0=RULE_VAR_NAME
					{
						newLeafNode(lv_varName_1_0, grammarAccess.getLiteralAccess().getVarNameVAR_NAMETerminalRuleCall_0_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getLiteralRule());
						}
						setWithLastConsumed(
							$current,
							"varName",
							lv_varName_1_0,
							"edu.ltl.wallin.LTL.VAR_NAME");
					}
				)
			)
			(
				otherlv_2='lower='
				{
					newLeafNode(otherlv_2, grammarAccess.getLiteralAccess().getLowerKeyword_0_2_0());
				}
				(
					(
						lv_lowerBound_3_0=RULE_INT
						{
							newLeafNode(lv_lowerBound_3_0, grammarAccess.getLiteralAccess().getLowerBoundINTTerminalRuleCall_0_2_1_0());
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getLiteralRule());
							}
							setWithLastConsumed(
								$current,
								"lowerBound",
								lv_lowerBound_3_0,
								"org.eclipse.xtext.common.Terminals.INT");
						}
					)
				)
			)?
			(
				otherlv_4='upper='
				{
					newLeafNode(otherlv_4, grammarAccess.getLiteralAccess().getUpperKeyword_0_3_0());
				}
				(
					(
						lv_upperBound_5_0=RULE_INT
						{
							newLeafNode(lv_upperBound_5_0, grammarAccess.getLiteralAccess().getUpperBoundINTTerminalRuleCall_0_3_1_0());
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getLiteralRule());
							}
							setWithLastConsumed(
								$current,
								"upperBound",
								lv_upperBound_5_0,
								"org.eclipse.xtext.common.Terminals.INT");
						}
					)
				)
			)?
		)
		    |
		(
			otherlv_6='('
			{
				newLeafNode(otherlv_6, grammarAccess.getLiteralAccess().getLeftParenthesisKeyword_1_0());
			}
			{
				newCompositeNode(grammarAccess.getLiteralAccess().getFormulaParserRuleCall_1_1());
			}
			this_Formula_7=ruleFormula
			{
				$current = $this_Formula_7.current;
				afterParserOrEnumRuleCall();
			}
			otherlv_8=')'
			{
				newLeafNode(otherlv_8, grammarAccess.getLiteralAccess().getRightParenthesisKeyword_1_2());
			}
		)
	)
;

RULE_VAR_NAME : 'a'..'z';

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
