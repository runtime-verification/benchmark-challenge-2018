package edu.ltl.wallin.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import edu.ltl.wallin.services.LTLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalLTLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_VAR_NAME", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'&'", "'|'", "'F'", "'G'", "'['", "','", "']'", "'lower='", "'upper='", "'('", "')'", "'U'", "'end'", "'-'"
    };
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int RULE_VAR_NAME=5;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=6;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_INT=4;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalLTLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalLTLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalLTLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalLTL.g"; }


    	private LTLGrammarAccess grammarAccess;

    	public void setGrammarAccess(LTLGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleFormula"
    // InternalLTL.g:54:1: entryRuleFormula : ruleFormula EOF ;
    public final void entryRuleFormula() throws RecognitionException {
        try {
            // InternalLTL.g:55:1: ( ruleFormula EOF )
            // InternalLTL.g:56:1: ruleFormula EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFormulaRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFormulaRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFormula"


    // $ANTLR start "ruleFormula"
    // InternalLTL.g:63:1: ruleFormula : ( ruleUntilFormula ) ;
    public final void ruleFormula() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:67:2: ( ( ruleUntilFormula ) )
            // InternalLTL.g:68:2: ( ruleUntilFormula )
            {
            // InternalLTL.g:68:2: ( ruleUntilFormula )
            // InternalLTL.g:69:3: ruleUntilFormula
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFormulaAccess().getUntilFormulaParserRuleCall()); 
            }
            pushFollow(FOLLOW_2);
            ruleUntilFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFormulaAccess().getUntilFormulaParserRuleCall()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFormula"


    // $ANTLR start "entryRuleUntilFormula"
    // InternalLTL.g:79:1: entryRuleUntilFormula : ruleUntilFormula EOF ;
    public final void entryRuleUntilFormula() throws RecognitionException {
        try {
            // InternalLTL.g:80:1: ( ruleUntilFormula EOF )
            // InternalLTL.g:81:1: ruleUntilFormula EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleUntilFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUntilFormula"


    // $ANTLR start "ruleUntilFormula"
    // InternalLTL.g:88:1: ruleUntilFormula : ( ( rule__UntilFormula__Group__0 ) ) ;
    public final void ruleUntilFormula() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:92:2: ( ( ( rule__UntilFormula__Group__0 ) ) )
            // InternalLTL.g:93:2: ( ( rule__UntilFormula__Group__0 ) )
            {
            // InternalLTL.g:93:2: ( ( rule__UntilFormula__Group__0 ) )
            // InternalLTL.g:94:3: ( rule__UntilFormula__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getGroup()); 
            }
            // InternalLTL.g:95:3: ( rule__UntilFormula__Group__0 )
            // InternalLTL.g:95:4: rule__UntilFormula__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUntilFormula"


    // $ANTLR start "entryRuleConnectiveFormula"
    // InternalLTL.g:104:1: entryRuleConnectiveFormula : ruleConnectiveFormula EOF ;
    public final void entryRuleConnectiveFormula() throws RecognitionException {
        try {
            // InternalLTL.g:105:1: ( ruleConnectiveFormula EOF )
            // InternalLTL.g:106:1: ruleConnectiveFormula EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleConnectiveFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConnectiveFormula"


    // $ANTLR start "ruleConnectiveFormula"
    // InternalLTL.g:113:1: ruleConnectiveFormula : ( ( rule__ConnectiveFormula__Group__0 ) ) ;
    public final void ruleConnectiveFormula() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:117:2: ( ( ( rule__ConnectiveFormula__Group__0 ) ) )
            // InternalLTL.g:118:2: ( ( rule__ConnectiveFormula__Group__0 ) )
            {
            // InternalLTL.g:118:2: ( ( rule__ConnectiveFormula__Group__0 ) )
            // InternalLTL.g:119:3: ( rule__ConnectiveFormula__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getGroup()); 
            }
            // InternalLTL.g:120:3: ( rule__ConnectiveFormula__Group__0 )
            // InternalLTL.g:120:4: rule__ConnectiveFormula__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConnectiveFormula"


    // $ANTLR start "entryRuleUnaryExpr"
    // InternalLTL.g:129:1: entryRuleUnaryExpr : ruleUnaryExpr EOF ;
    public final void entryRuleUnaryExpr() throws RecognitionException {
        try {
            // InternalLTL.g:130:1: ( ruleUnaryExpr EOF )
            // InternalLTL.g:131:1: ruleUnaryExpr EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleUnaryExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnaryExpr"


    // $ANTLR start "ruleUnaryExpr"
    // InternalLTL.g:138:1: ruleUnaryExpr : ( ( rule__UnaryExpr__Alternatives ) ) ;
    public final void ruleUnaryExpr() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:142:2: ( ( ( rule__UnaryExpr__Alternatives ) ) )
            // InternalLTL.g:143:2: ( ( rule__UnaryExpr__Alternatives ) )
            {
            // InternalLTL.g:143:2: ( ( rule__UnaryExpr__Alternatives ) )
            // InternalLTL.g:144:3: ( rule__UnaryExpr__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getAlternatives()); 
            }
            // InternalLTL.g:145:3: ( rule__UnaryExpr__Alternatives )
            // InternalLTL.g:145:4: rule__UnaryExpr__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnaryExpr"


    // $ANTLR start "entryRuleLiteral"
    // InternalLTL.g:154:1: entryRuleLiteral : ruleLiteral EOF ;
    public final void entryRuleLiteral() throws RecognitionException {
        try {
            // InternalLTL.g:155:1: ( ruleLiteral EOF )
            // InternalLTL.g:156:1: ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleLiteral();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // InternalLTL.g:163:1: ruleLiteral : ( ( rule__Literal__Alternatives ) ) ;
    public final void ruleLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:167:2: ( ( ( rule__Literal__Alternatives ) ) )
            // InternalLTL.g:168:2: ( ( rule__Literal__Alternatives ) )
            {
            // InternalLTL.g:168:2: ( ( rule__Literal__Alternatives ) )
            // InternalLTL.g:169:3: ( rule__Literal__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getAlternatives()); 
            }
            // InternalLTL.g:170:3: ( rule__Literal__Alternatives )
            // InternalLTL.g:170:4: rule__Literal__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getAlternatives()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0"
    // InternalLTL.g:178:1: rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 : ( ( '&' ) | ( '|' ) );
    public final void rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:182:1: ( ( '&' ) | ( '|' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            else if ( (LA1_0==13) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalLTL.g:183:2: ( '&' )
                    {
                    // InternalLTL.g:183:2: ( '&' )
                    // InternalLTL.g:184:3: '&'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getConnectiveFormulaAccess().getOpAmpersandKeyword_1_0_0_1_0_0()); 
                    }
                    match(input,12,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getConnectiveFormulaAccess().getOpAmpersandKeyword_1_0_0_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLTL.g:189:2: ( '|' )
                    {
                    // InternalLTL.g:189:2: ( '|' )
                    // InternalLTL.g:190:3: '|'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getConnectiveFormulaAccess().getOpVerticalLineKeyword_1_0_0_1_0_1()); 
                    }
                    match(input,13,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getConnectiveFormulaAccess().getOpVerticalLineKeyword_1_0_0_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0"


    // $ANTLR start "rule__UnaryExpr__Alternatives"
    // InternalLTL.g:199:1: rule__UnaryExpr__Alternatives : ( ( ( rule__UnaryExpr__Group_0__0 ) ) | ( ( rule__UnaryExpr__Group_1__0 ) ) | ( ruleLiteral ) );
    public final void rule__UnaryExpr__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:203:1: ( ( ( rule__UnaryExpr__Group_0__0 ) ) | ( ( rule__UnaryExpr__Group_1__0 ) ) | ( ruleLiteral ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 14:
            case 15:
                {
                alt2=1;
                }
                break;
            case 25:
                {
                alt2=2;
                }
                break;
            case RULE_VAR_NAME:
            case 21:
                {
                alt2=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalLTL.g:204:2: ( ( rule__UnaryExpr__Group_0__0 ) )
                    {
                    // InternalLTL.g:204:2: ( ( rule__UnaryExpr__Group_0__0 ) )
                    // InternalLTL.g:205:3: ( rule__UnaryExpr__Group_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExprAccess().getGroup_0()); 
                    }
                    // InternalLTL.g:206:3: ( rule__UnaryExpr__Group_0__0 )
                    // InternalLTL.g:206:4: rule__UnaryExpr__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UnaryExpr__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExprAccess().getGroup_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLTL.g:210:2: ( ( rule__UnaryExpr__Group_1__0 ) )
                    {
                    // InternalLTL.g:210:2: ( ( rule__UnaryExpr__Group_1__0 ) )
                    // InternalLTL.g:211:3: ( rule__UnaryExpr__Group_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExprAccess().getGroup_1()); 
                    }
                    // InternalLTL.g:212:3: ( rule__UnaryExpr__Group_1__0 )
                    // InternalLTL.g:212:4: rule__UnaryExpr__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__UnaryExpr__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExprAccess().getGroup_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalLTL.g:216:2: ( ruleLiteral )
                    {
                    // InternalLTL.g:216:2: ( ruleLiteral )
                    // InternalLTL.g:217:3: ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExprAccess().getLiteralParserRuleCall_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleLiteral();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExprAccess().getLiteralParserRuleCall_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Alternatives"


    // $ANTLR start "rule__UnaryExpr__OpAlternatives_0_1_0"
    // InternalLTL.g:226:1: rule__UnaryExpr__OpAlternatives_0_1_0 : ( ( 'F' ) | ( 'G' ) );
    public final void rule__UnaryExpr__OpAlternatives_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:230:1: ( ( 'F' ) | ( 'G' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==15) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalLTL.g:231:2: ( 'F' )
                    {
                    // InternalLTL.g:231:2: ( 'F' )
                    // InternalLTL.g:232:3: 'F'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExprAccess().getOpFKeyword_0_1_0_0()); 
                    }
                    match(input,14,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExprAccess().getOpFKeyword_0_1_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLTL.g:237:2: ( 'G' )
                    {
                    // InternalLTL.g:237:2: ( 'G' )
                    // InternalLTL.g:238:3: 'G'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUnaryExprAccess().getOpGKeyword_0_1_0_1()); 
                    }
                    match(input,15,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUnaryExprAccess().getOpGKeyword_0_1_0_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__OpAlternatives_0_1_0"


    // $ANTLR start "rule__Literal__Alternatives"
    // InternalLTL.g:247:1: rule__Literal__Alternatives : ( ( ( rule__Literal__Group_0__0 ) ) | ( ( rule__Literal__Group_1__0 ) ) );
    public final void rule__Literal__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:251:1: ( ( ( rule__Literal__Group_0__0 ) ) | ( ( rule__Literal__Group_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_VAR_NAME) ) {
                alt4=1;
            }
            else if ( (LA4_0==21) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalLTL.g:252:2: ( ( rule__Literal__Group_0__0 ) )
                    {
                    // InternalLTL.g:252:2: ( ( rule__Literal__Group_0__0 ) )
                    // InternalLTL.g:253:3: ( rule__Literal__Group_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getGroup_0()); 
                    }
                    // InternalLTL.g:254:3: ( rule__Literal__Group_0__0 )
                    // InternalLTL.g:254:4: rule__Literal__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Literal__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getGroup_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLTL.g:258:2: ( ( rule__Literal__Group_1__0 ) )
                    {
                    // InternalLTL.g:258:2: ( ( rule__Literal__Group_1__0 ) )
                    // InternalLTL.g:259:3: ( rule__Literal__Group_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLiteralAccess().getGroup_1()); 
                    }
                    // InternalLTL.g:260:3: ( rule__Literal__Group_1__0 )
                    // InternalLTL.g:260:4: rule__Literal__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Literal__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLiteralAccess().getGroup_1()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Alternatives"


    // $ANTLR start "rule__UntilFormula__Group__0"
    // InternalLTL.g:268:1: rule__UntilFormula__Group__0 : rule__UntilFormula__Group__0__Impl rule__UntilFormula__Group__1 ;
    public final void rule__UntilFormula__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:272:1: ( rule__UntilFormula__Group__0__Impl rule__UntilFormula__Group__1 )
            // InternalLTL.g:273:2: rule__UntilFormula__Group__0__Impl rule__UntilFormula__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__UntilFormula__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group__0"


    // $ANTLR start "rule__UntilFormula__Group__0__Impl"
    // InternalLTL.g:280:1: rule__UntilFormula__Group__0__Impl : ( ruleConnectiveFormula ) ;
    public final void rule__UntilFormula__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:284:1: ( ( ruleConnectiveFormula ) )
            // InternalLTL.g:285:1: ( ruleConnectiveFormula )
            {
            // InternalLTL.g:285:1: ( ruleConnectiveFormula )
            // InternalLTL.g:286:2: ruleConnectiveFormula
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getConnectiveFormulaParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConnectiveFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getConnectiveFormulaParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group__0__Impl"


    // $ANTLR start "rule__UntilFormula__Group__1"
    // InternalLTL.g:295:1: rule__UntilFormula__Group__1 : rule__UntilFormula__Group__1__Impl ;
    public final void rule__UntilFormula__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:299:1: ( rule__UntilFormula__Group__1__Impl )
            // InternalLTL.g:300:2: rule__UntilFormula__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group__1"


    // $ANTLR start "rule__UntilFormula__Group__1__Impl"
    // InternalLTL.g:306:1: rule__UntilFormula__Group__1__Impl : ( ( rule__UntilFormula__Group_1__0 )* ) ;
    public final void rule__UntilFormula__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:310:1: ( ( ( rule__UntilFormula__Group_1__0 )* ) )
            // InternalLTL.g:311:1: ( ( rule__UntilFormula__Group_1__0 )* )
            {
            // InternalLTL.g:311:1: ( ( rule__UntilFormula__Group_1__0 )* )
            // InternalLTL.g:312:2: ( rule__UntilFormula__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getGroup_1()); 
            }
            // InternalLTL.g:313:2: ( rule__UntilFormula__Group_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==23) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalLTL.g:313:3: rule__UntilFormula__Group_1__0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__UntilFormula__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group__1__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1__0"
    // InternalLTL.g:322:1: rule__UntilFormula__Group_1__0 : rule__UntilFormula__Group_1__0__Impl rule__UntilFormula__Group_1__1 ;
    public final void rule__UntilFormula__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:326:1: ( rule__UntilFormula__Group_1__0__Impl rule__UntilFormula__Group_1__1 )
            // InternalLTL.g:327:2: rule__UntilFormula__Group_1__0__Impl rule__UntilFormula__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__UntilFormula__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1__0"


    // $ANTLR start "rule__UntilFormula__Group_1__0__Impl"
    // InternalLTL.g:334:1: rule__UntilFormula__Group_1__0__Impl : ( ( rule__UntilFormula__Group_1_0__0 ) ) ;
    public final void rule__UntilFormula__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:338:1: ( ( ( rule__UntilFormula__Group_1_0__0 ) ) )
            // InternalLTL.g:339:1: ( ( rule__UntilFormula__Group_1_0__0 ) )
            {
            // InternalLTL.g:339:1: ( ( rule__UntilFormula__Group_1_0__0 ) )
            // InternalLTL.g:340:2: ( rule__UntilFormula__Group_1_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getGroup_1_0()); 
            }
            // InternalLTL.g:341:2: ( rule__UntilFormula__Group_1_0__0 )
            // InternalLTL.g:341:3: rule__UntilFormula__Group_1_0__0
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getGroup_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1__0__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1__1"
    // InternalLTL.g:349:1: rule__UntilFormula__Group_1__1 : rule__UntilFormula__Group_1__1__Impl ;
    public final void rule__UntilFormula__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:353:1: ( rule__UntilFormula__Group_1__1__Impl )
            // InternalLTL.g:354:2: rule__UntilFormula__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1__1"


    // $ANTLR start "rule__UntilFormula__Group_1__1__Impl"
    // InternalLTL.g:360:1: rule__UntilFormula__Group_1__1__Impl : ( ( rule__UntilFormula__RightAssignment_1_1 ) ) ;
    public final void rule__UntilFormula__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:364:1: ( ( ( rule__UntilFormula__RightAssignment_1_1 ) ) )
            // InternalLTL.g:365:1: ( ( rule__UntilFormula__RightAssignment_1_1 ) )
            {
            // InternalLTL.g:365:1: ( ( rule__UntilFormula__RightAssignment_1_1 ) )
            // InternalLTL.g:366:2: ( rule__UntilFormula__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getRightAssignment_1_1()); 
            }
            // InternalLTL.g:367:2: ( rule__UntilFormula__RightAssignment_1_1 )
            // InternalLTL.g:367:3: rule__UntilFormula__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__RightAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getRightAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1__1__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0__0"
    // InternalLTL.g:376:1: rule__UntilFormula__Group_1_0__0 : rule__UntilFormula__Group_1_0__0__Impl ;
    public final void rule__UntilFormula__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:380:1: ( rule__UntilFormula__Group_1_0__0__Impl )
            // InternalLTL.g:381:2: rule__UntilFormula__Group_1_0__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0__0"


    // $ANTLR start "rule__UntilFormula__Group_1_0__0__Impl"
    // InternalLTL.g:387:1: rule__UntilFormula__Group_1_0__0__Impl : ( ( rule__UntilFormula__Group_1_0_0__0 ) ) ;
    public final void rule__UntilFormula__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:391:1: ( ( ( rule__UntilFormula__Group_1_0_0__0 ) ) )
            // InternalLTL.g:392:1: ( ( rule__UntilFormula__Group_1_0_0__0 ) )
            {
            // InternalLTL.g:392:1: ( ( rule__UntilFormula__Group_1_0_0__0 ) )
            // InternalLTL.g:393:2: ( rule__UntilFormula__Group_1_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getGroup_1_0_0()); 
            }
            // InternalLTL.g:394:2: ( rule__UntilFormula__Group_1_0_0__0 )
            // InternalLTL.g:394:3: rule__UntilFormula__Group_1_0_0__0
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getGroup_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0__0__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__0"
    // InternalLTL.g:403:1: rule__UntilFormula__Group_1_0_0__0 : rule__UntilFormula__Group_1_0_0__0__Impl rule__UntilFormula__Group_1_0_0__1 ;
    public final void rule__UntilFormula__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:407:1: ( rule__UntilFormula__Group_1_0_0__0__Impl rule__UntilFormula__Group_1_0_0__1 )
            // InternalLTL.g:408:2: rule__UntilFormula__Group_1_0_0__0__Impl rule__UntilFormula__Group_1_0_0__1
            {
            pushFollow(FOLLOW_3);
            rule__UntilFormula__Group_1_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__0"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__0__Impl"
    // InternalLTL.g:415:1: rule__UntilFormula__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__UntilFormula__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:419:1: ( ( () ) )
            // InternalLTL.g:420:1: ( () )
            {
            // InternalLTL.g:420:1: ( () )
            // InternalLTL.g:421:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getBinaryExprLeftAction_1_0_0_0()); 
            }
            // InternalLTL.g:422:2: ()
            // InternalLTL.g:422:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getBinaryExprLeftAction_1_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__1"
    // InternalLTL.g:430:1: rule__UntilFormula__Group_1_0_0__1 : rule__UntilFormula__Group_1_0_0__1__Impl rule__UntilFormula__Group_1_0_0__2 ;
    public final void rule__UntilFormula__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:434:1: ( rule__UntilFormula__Group_1_0_0__1__Impl rule__UntilFormula__Group_1_0_0__2 )
            // InternalLTL.g:435:2: rule__UntilFormula__Group_1_0_0__1__Impl rule__UntilFormula__Group_1_0_0__2
            {
            pushFollow(FOLLOW_6);
            rule__UntilFormula__Group_1_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__1"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__1__Impl"
    // InternalLTL.g:442:1: rule__UntilFormula__Group_1_0_0__1__Impl : ( ( rule__UntilFormula__OpAssignment_1_0_0_1 ) ) ;
    public final void rule__UntilFormula__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:446:1: ( ( ( rule__UntilFormula__OpAssignment_1_0_0_1 ) ) )
            // InternalLTL.g:447:1: ( ( rule__UntilFormula__OpAssignment_1_0_0_1 ) )
            {
            // InternalLTL.g:447:1: ( ( rule__UntilFormula__OpAssignment_1_0_0_1 ) )
            // InternalLTL.g:448:2: ( rule__UntilFormula__OpAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getOpAssignment_1_0_0_1()); 
            }
            // InternalLTL.g:449:2: ( rule__UntilFormula__OpAssignment_1_0_0_1 )
            // InternalLTL.g:449:3: rule__UntilFormula__OpAssignment_1_0_0_1
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__OpAssignment_1_0_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getOpAssignment_1_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__2"
    // InternalLTL.g:457:1: rule__UntilFormula__Group_1_0_0__2 : rule__UntilFormula__Group_1_0_0__2__Impl rule__UntilFormula__Group_1_0_0__3 ;
    public final void rule__UntilFormula__Group_1_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:461:1: ( rule__UntilFormula__Group_1_0_0__2__Impl rule__UntilFormula__Group_1_0_0__3 )
            // InternalLTL.g:462:2: rule__UntilFormula__Group_1_0_0__2__Impl rule__UntilFormula__Group_1_0_0__3
            {
            pushFollow(FOLLOW_7);
            rule__UntilFormula__Group_1_0_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__2"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__2__Impl"
    // InternalLTL.g:469:1: rule__UntilFormula__Group_1_0_0__2__Impl : ( '[' ) ;
    public final void rule__UntilFormula__Group_1_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:473:1: ( ( '[' ) )
            // InternalLTL.g:474:1: ( '[' )
            {
            // InternalLTL.g:474:1: ( '[' )
            // InternalLTL.g:475:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getLeftSquareBracketKeyword_1_0_0_2()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getLeftSquareBracketKeyword_1_0_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__2__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__3"
    // InternalLTL.g:484:1: rule__UntilFormula__Group_1_0_0__3 : rule__UntilFormula__Group_1_0_0__3__Impl rule__UntilFormula__Group_1_0_0__4 ;
    public final void rule__UntilFormula__Group_1_0_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:488:1: ( rule__UntilFormula__Group_1_0_0__3__Impl rule__UntilFormula__Group_1_0_0__4 )
            // InternalLTL.g:489:2: rule__UntilFormula__Group_1_0_0__3__Impl rule__UntilFormula__Group_1_0_0__4
            {
            pushFollow(FOLLOW_7);
            rule__UntilFormula__Group_1_0_0__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__3"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__3__Impl"
    // InternalLTL.g:496:1: rule__UntilFormula__Group_1_0_0__3__Impl : ( ( rule__UntilFormula__LowerBoundAssignment_1_0_0_3 )? ) ;
    public final void rule__UntilFormula__Group_1_0_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:500:1: ( ( ( rule__UntilFormula__LowerBoundAssignment_1_0_0_3 )? ) )
            // InternalLTL.g:501:1: ( ( rule__UntilFormula__LowerBoundAssignment_1_0_0_3 )? )
            {
            // InternalLTL.g:501:1: ( ( rule__UntilFormula__LowerBoundAssignment_1_0_0_3 )? )
            // InternalLTL.g:502:2: ( rule__UntilFormula__LowerBoundAssignment_1_0_0_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getLowerBoundAssignment_1_0_0_3()); 
            }
            // InternalLTL.g:503:2: ( rule__UntilFormula__LowerBoundAssignment_1_0_0_3 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_INT) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalLTL.g:503:3: rule__UntilFormula__LowerBoundAssignment_1_0_0_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__UntilFormula__LowerBoundAssignment_1_0_0_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getLowerBoundAssignment_1_0_0_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__3__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__4"
    // InternalLTL.g:511:1: rule__UntilFormula__Group_1_0_0__4 : rule__UntilFormula__Group_1_0_0__4__Impl rule__UntilFormula__Group_1_0_0__5 ;
    public final void rule__UntilFormula__Group_1_0_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:515:1: ( rule__UntilFormula__Group_1_0_0__4__Impl rule__UntilFormula__Group_1_0_0__5 )
            // InternalLTL.g:516:2: rule__UntilFormula__Group_1_0_0__4__Impl rule__UntilFormula__Group_1_0_0__5
            {
            pushFollow(FOLLOW_8);
            rule__UntilFormula__Group_1_0_0__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__4"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__4__Impl"
    // InternalLTL.g:523:1: rule__UntilFormula__Group_1_0_0__4__Impl : ( ',' ) ;
    public final void rule__UntilFormula__Group_1_0_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:527:1: ( ( ',' ) )
            // InternalLTL.g:528:1: ( ',' )
            {
            // InternalLTL.g:528:1: ( ',' )
            // InternalLTL.g:529:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getCommaKeyword_1_0_0_4()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getCommaKeyword_1_0_0_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__4__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__5"
    // InternalLTL.g:538:1: rule__UntilFormula__Group_1_0_0__5 : rule__UntilFormula__Group_1_0_0__5__Impl rule__UntilFormula__Group_1_0_0__6 ;
    public final void rule__UntilFormula__Group_1_0_0__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:542:1: ( rule__UntilFormula__Group_1_0_0__5__Impl rule__UntilFormula__Group_1_0_0__6 )
            // InternalLTL.g:543:2: rule__UntilFormula__Group_1_0_0__5__Impl rule__UntilFormula__Group_1_0_0__6
            {
            pushFollow(FOLLOW_8);
            rule__UntilFormula__Group_1_0_0__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__5"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__5__Impl"
    // InternalLTL.g:550:1: rule__UntilFormula__Group_1_0_0__5__Impl : ( ( rule__UntilFormula__EndAssignment_1_0_0_5 )? ) ;
    public final void rule__UntilFormula__Group_1_0_0__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:554:1: ( ( ( rule__UntilFormula__EndAssignment_1_0_0_5 )? ) )
            // InternalLTL.g:555:1: ( ( rule__UntilFormula__EndAssignment_1_0_0_5 )? )
            {
            // InternalLTL.g:555:1: ( ( rule__UntilFormula__EndAssignment_1_0_0_5 )? )
            // InternalLTL.g:556:2: ( rule__UntilFormula__EndAssignment_1_0_0_5 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getEndAssignment_1_0_0_5()); 
            }
            // InternalLTL.g:557:2: ( rule__UntilFormula__EndAssignment_1_0_0_5 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==24) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalLTL.g:557:3: rule__UntilFormula__EndAssignment_1_0_0_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__UntilFormula__EndAssignment_1_0_0_5();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getEndAssignment_1_0_0_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__5__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__6"
    // InternalLTL.g:565:1: rule__UntilFormula__Group_1_0_0__6 : rule__UntilFormula__Group_1_0_0__6__Impl rule__UntilFormula__Group_1_0_0__7 ;
    public final void rule__UntilFormula__Group_1_0_0__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:569:1: ( rule__UntilFormula__Group_1_0_0__6__Impl rule__UntilFormula__Group_1_0_0__7 )
            // InternalLTL.g:570:2: rule__UntilFormula__Group_1_0_0__6__Impl rule__UntilFormula__Group_1_0_0__7
            {
            pushFollow(FOLLOW_8);
            rule__UntilFormula__Group_1_0_0__6__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__7();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__6"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__6__Impl"
    // InternalLTL.g:577:1: rule__UntilFormula__Group_1_0_0__6__Impl : ( ( rule__UntilFormula__UpperBoundAssignment_1_0_0_6 )? ) ;
    public final void rule__UntilFormula__Group_1_0_0__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:581:1: ( ( ( rule__UntilFormula__UpperBoundAssignment_1_0_0_6 )? ) )
            // InternalLTL.g:582:1: ( ( rule__UntilFormula__UpperBoundAssignment_1_0_0_6 )? )
            {
            // InternalLTL.g:582:1: ( ( rule__UntilFormula__UpperBoundAssignment_1_0_0_6 )? )
            // InternalLTL.g:583:2: ( rule__UntilFormula__UpperBoundAssignment_1_0_0_6 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getUpperBoundAssignment_1_0_0_6()); 
            }
            // InternalLTL.g:584:2: ( rule__UntilFormula__UpperBoundAssignment_1_0_0_6 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_INT) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalLTL.g:584:3: rule__UntilFormula__UpperBoundAssignment_1_0_0_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__UntilFormula__UpperBoundAssignment_1_0_0_6();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getUpperBoundAssignment_1_0_0_6()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__6__Impl"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__7"
    // InternalLTL.g:592:1: rule__UntilFormula__Group_1_0_0__7 : rule__UntilFormula__Group_1_0_0__7__Impl ;
    public final void rule__UntilFormula__Group_1_0_0__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:596:1: ( rule__UntilFormula__Group_1_0_0__7__Impl )
            // InternalLTL.g:597:2: rule__UntilFormula__Group_1_0_0__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UntilFormula__Group_1_0_0__7__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__7"


    // $ANTLR start "rule__UntilFormula__Group_1_0_0__7__Impl"
    // InternalLTL.g:603:1: rule__UntilFormula__Group_1_0_0__7__Impl : ( ']' ) ;
    public final void rule__UntilFormula__Group_1_0_0__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:607:1: ( ( ']' ) )
            // InternalLTL.g:608:1: ( ']' )
            {
            // InternalLTL.g:608:1: ( ']' )
            // InternalLTL.g:609:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getRightSquareBracketKeyword_1_0_0_7()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getRightSquareBracketKeyword_1_0_0_7()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__Group_1_0_0__7__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group__0"
    // InternalLTL.g:619:1: rule__ConnectiveFormula__Group__0 : rule__ConnectiveFormula__Group__0__Impl rule__ConnectiveFormula__Group__1 ;
    public final void rule__ConnectiveFormula__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:623:1: ( rule__ConnectiveFormula__Group__0__Impl rule__ConnectiveFormula__Group__1 )
            // InternalLTL.g:624:2: rule__ConnectiveFormula__Group__0__Impl rule__ConnectiveFormula__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ConnectiveFormula__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group__0"


    // $ANTLR start "rule__ConnectiveFormula__Group__0__Impl"
    // InternalLTL.g:631:1: rule__ConnectiveFormula__Group__0__Impl : ( ruleUnaryExpr ) ;
    public final void rule__ConnectiveFormula__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:635:1: ( ( ruleUnaryExpr ) )
            // InternalLTL.g:636:1: ( ruleUnaryExpr )
            {
            // InternalLTL.g:636:1: ( ruleUnaryExpr )
            // InternalLTL.g:637:2: ruleUnaryExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getUnaryExprParserRuleCall_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleUnaryExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getUnaryExprParserRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group__0__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group__1"
    // InternalLTL.g:646:1: rule__ConnectiveFormula__Group__1 : rule__ConnectiveFormula__Group__1__Impl ;
    public final void rule__ConnectiveFormula__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:650:1: ( rule__ConnectiveFormula__Group__1__Impl )
            // InternalLTL.g:651:2: rule__ConnectiveFormula__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group__1"


    // $ANTLR start "rule__ConnectiveFormula__Group__1__Impl"
    // InternalLTL.g:657:1: rule__ConnectiveFormula__Group__1__Impl : ( ( rule__ConnectiveFormula__Group_1__0 )* ) ;
    public final void rule__ConnectiveFormula__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:661:1: ( ( ( rule__ConnectiveFormula__Group_1__0 )* ) )
            // InternalLTL.g:662:1: ( ( rule__ConnectiveFormula__Group_1__0 )* )
            {
            // InternalLTL.g:662:1: ( ( rule__ConnectiveFormula__Group_1__0 )* )
            // InternalLTL.g:663:2: ( rule__ConnectiveFormula__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getGroup_1()); 
            }
            // InternalLTL.g:664:2: ( rule__ConnectiveFormula__Group_1__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=12 && LA9_0<=13)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalLTL.g:664:3: rule__ConnectiveFormula__Group_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ConnectiveFormula__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getGroup_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group__1__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group_1__0"
    // InternalLTL.g:673:1: rule__ConnectiveFormula__Group_1__0 : rule__ConnectiveFormula__Group_1__0__Impl rule__ConnectiveFormula__Group_1__1 ;
    public final void rule__ConnectiveFormula__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:677:1: ( rule__ConnectiveFormula__Group_1__0__Impl rule__ConnectiveFormula__Group_1__1 )
            // InternalLTL.g:678:2: rule__ConnectiveFormula__Group_1__0__Impl rule__ConnectiveFormula__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__ConnectiveFormula__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1__0"


    // $ANTLR start "rule__ConnectiveFormula__Group_1__0__Impl"
    // InternalLTL.g:685:1: rule__ConnectiveFormula__Group_1__0__Impl : ( ( rule__ConnectiveFormula__Group_1_0__0 ) ) ;
    public final void rule__ConnectiveFormula__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:689:1: ( ( ( rule__ConnectiveFormula__Group_1_0__0 ) ) )
            // InternalLTL.g:690:1: ( ( rule__ConnectiveFormula__Group_1_0__0 ) )
            {
            // InternalLTL.g:690:1: ( ( rule__ConnectiveFormula__Group_1_0__0 ) )
            // InternalLTL.g:691:2: ( rule__ConnectiveFormula__Group_1_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getGroup_1_0()); 
            }
            // InternalLTL.g:692:2: ( rule__ConnectiveFormula__Group_1_0__0 )
            // InternalLTL.g:692:3: rule__ConnectiveFormula__Group_1_0__0
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getGroup_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1__0__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group_1__1"
    // InternalLTL.g:700:1: rule__ConnectiveFormula__Group_1__1 : rule__ConnectiveFormula__Group_1__1__Impl ;
    public final void rule__ConnectiveFormula__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:704:1: ( rule__ConnectiveFormula__Group_1__1__Impl )
            // InternalLTL.g:705:2: rule__ConnectiveFormula__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1__1"


    // $ANTLR start "rule__ConnectiveFormula__Group_1__1__Impl"
    // InternalLTL.g:711:1: rule__ConnectiveFormula__Group_1__1__Impl : ( ( rule__ConnectiveFormula__RightAssignment_1_1 ) ) ;
    public final void rule__ConnectiveFormula__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:715:1: ( ( ( rule__ConnectiveFormula__RightAssignment_1_1 ) ) )
            // InternalLTL.g:716:1: ( ( rule__ConnectiveFormula__RightAssignment_1_1 ) )
            {
            // InternalLTL.g:716:1: ( ( rule__ConnectiveFormula__RightAssignment_1_1 ) )
            // InternalLTL.g:717:2: ( rule__ConnectiveFormula__RightAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getRightAssignment_1_1()); 
            }
            // InternalLTL.g:718:2: ( rule__ConnectiveFormula__RightAssignment_1_1 )
            // InternalLTL.g:718:3: rule__ConnectiveFormula__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__RightAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getRightAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1__1__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group_1_0__0"
    // InternalLTL.g:727:1: rule__ConnectiveFormula__Group_1_0__0 : rule__ConnectiveFormula__Group_1_0__0__Impl ;
    public final void rule__ConnectiveFormula__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:731:1: ( rule__ConnectiveFormula__Group_1_0__0__Impl )
            // InternalLTL.g:732:2: rule__ConnectiveFormula__Group_1_0__0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1_0__0"


    // $ANTLR start "rule__ConnectiveFormula__Group_1_0__0__Impl"
    // InternalLTL.g:738:1: rule__ConnectiveFormula__Group_1_0__0__Impl : ( ( rule__ConnectiveFormula__Group_1_0_0__0 ) ) ;
    public final void rule__ConnectiveFormula__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:742:1: ( ( ( rule__ConnectiveFormula__Group_1_0_0__0 ) ) )
            // InternalLTL.g:743:1: ( ( rule__ConnectiveFormula__Group_1_0_0__0 ) )
            {
            // InternalLTL.g:743:1: ( ( rule__ConnectiveFormula__Group_1_0_0__0 ) )
            // InternalLTL.g:744:2: ( rule__ConnectiveFormula__Group_1_0_0__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getGroup_1_0_0()); 
            }
            // InternalLTL.g:745:2: ( rule__ConnectiveFormula__Group_1_0_0__0 )
            // InternalLTL.g:745:3: rule__ConnectiveFormula__Group_1_0_0__0
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1_0_0__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getGroup_1_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1_0__0__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group_1_0_0__0"
    // InternalLTL.g:754:1: rule__ConnectiveFormula__Group_1_0_0__0 : rule__ConnectiveFormula__Group_1_0_0__0__Impl rule__ConnectiveFormula__Group_1_0_0__1 ;
    public final void rule__ConnectiveFormula__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:758:1: ( rule__ConnectiveFormula__Group_1_0_0__0__Impl rule__ConnectiveFormula__Group_1_0_0__1 )
            // InternalLTL.g:759:2: rule__ConnectiveFormula__Group_1_0_0__0__Impl rule__ConnectiveFormula__Group_1_0_0__1
            {
            pushFollow(FOLLOW_9);
            rule__ConnectiveFormula__Group_1_0_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1_0_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1_0_0__0"


    // $ANTLR start "rule__ConnectiveFormula__Group_1_0_0__0__Impl"
    // InternalLTL.g:766:1: rule__ConnectiveFormula__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__ConnectiveFormula__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:770:1: ( ( () ) )
            // InternalLTL.g:771:1: ( () )
            {
            // InternalLTL.g:771:1: ( () )
            // InternalLTL.g:772:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getBinaryExprLeftAction_1_0_0_0()); 
            }
            // InternalLTL.g:773:2: ()
            // InternalLTL.g:773:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getBinaryExprLeftAction_1_0_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__ConnectiveFormula__Group_1_0_0__1"
    // InternalLTL.g:781:1: rule__ConnectiveFormula__Group_1_0_0__1 : rule__ConnectiveFormula__Group_1_0_0__1__Impl ;
    public final void rule__ConnectiveFormula__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:785:1: ( rule__ConnectiveFormula__Group_1_0_0__1__Impl )
            // InternalLTL.g:786:2: rule__ConnectiveFormula__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__Group_1_0_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1_0_0__1"


    // $ANTLR start "rule__ConnectiveFormula__Group_1_0_0__1__Impl"
    // InternalLTL.g:792:1: rule__ConnectiveFormula__Group_1_0_0__1__Impl : ( ( rule__ConnectiveFormula__OpAssignment_1_0_0_1 ) ) ;
    public final void rule__ConnectiveFormula__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:796:1: ( ( ( rule__ConnectiveFormula__OpAssignment_1_0_0_1 ) ) )
            // InternalLTL.g:797:1: ( ( rule__ConnectiveFormula__OpAssignment_1_0_0_1 ) )
            {
            // InternalLTL.g:797:1: ( ( rule__ConnectiveFormula__OpAssignment_1_0_0_1 ) )
            // InternalLTL.g:798:2: ( rule__ConnectiveFormula__OpAssignment_1_0_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getOpAssignment_1_0_0_1()); 
            }
            // InternalLTL.g:799:2: ( rule__ConnectiveFormula__OpAssignment_1_0_0_1 )
            // InternalLTL.g:799:3: rule__ConnectiveFormula__OpAssignment_1_0_0_1
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__OpAssignment_1_0_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getOpAssignment_1_0_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__0"
    // InternalLTL.g:808:1: rule__UnaryExpr__Group_0__0 : rule__UnaryExpr__Group_0__0__Impl rule__UnaryExpr__Group_0__1 ;
    public final void rule__UnaryExpr__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:812:1: ( rule__UnaryExpr__Group_0__0__Impl rule__UnaryExpr__Group_0__1 )
            // InternalLTL.g:813:2: rule__UnaryExpr__Group_0__0__Impl rule__UnaryExpr__Group_0__1
            {
            pushFollow(FOLLOW_11);
            rule__UnaryExpr__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__0"


    // $ANTLR start "rule__UnaryExpr__Group_0__0__Impl"
    // InternalLTL.g:820:1: rule__UnaryExpr__Group_0__0__Impl : ( () ) ;
    public final void rule__UnaryExpr__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:824:1: ( ( () ) )
            // InternalLTL.g:825:1: ( () )
            {
            // InternalLTL.g:825:1: ( () )
            // InternalLTL.g:826:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getUnaryExprAction_0_0()); 
            }
            // InternalLTL.g:827:2: ()
            // InternalLTL.g:827:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getUnaryExprAction_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__0__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__1"
    // InternalLTL.g:835:1: rule__UnaryExpr__Group_0__1 : rule__UnaryExpr__Group_0__1__Impl rule__UnaryExpr__Group_0__2 ;
    public final void rule__UnaryExpr__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:839:1: ( rule__UnaryExpr__Group_0__1__Impl rule__UnaryExpr__Group_0__2 )
            // InternalLTL.g:840:2: rule__UnaryExpr__Group_0__1__Impl rule__UnaryExpr__Group_0__2
            {
            pushFollow(FOLLOW_6);
            rule__UnaryExpr__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__1"


    // $ANTLR start "rule__UnaryExpr__Group_0__1__Impl"
    // InternalLTL.g:847:1: rule__UnaryExpr__Group_0__1__Impl : ( ( rule__UnaryExpr__OpAssignment_0_1 ) ) ;
    public final void rule__UnaryExpr__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:851:1: ( ( ( rule__UnaryExpr__OpAssignment_0_1 ) ) )
            // InternalLTL.g:852:1: ( ( rule__UnaryExpr__OpAssignment_0_1 ) )
            {
            // InternalLTL.g:852:1: ( ( rule__UnaryExpr__OpAssignment_0_1 ) )
            // InternalLTL.g:853:2: ( rule__UnaryExpr__OpAssignment_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getOpAssignment_0_1()); 
            }
            // InternalLTL.g:854:2: ( rule__UnaryExpr__OpAssignment_0_1 )
            // InternalLTL.g:854:3: rule__UnaryExpr__OpAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__OpAssignment_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getOpAssignment_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__1__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__2"
    // InternalLTL.g:862:1: rule__UnaryExpr__Group_0__2 : rule__UnaryExpr__Group_0__2__Impl rule__UnaryExpr__Group_0__3 ;
    public final void rule__UnaryExpr__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:866:1: ( rule__UnaryExpr__Group_0__2__Impl rule__UnaryExpr__Group_0__3 )
            // InternalLTL.g:867:2: rule__UnaryExpr__Group_0__2__Impl rule__UnaryExpr__Group_0__3
            {
            pushFollow(FOLLOW_7);
            rule__UnaryExpr__Group_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__2"


    // $ANTLR start "rule__UnaryExpr__Group_0__2__Impl"
    // InternalLTL.g:874:1: rule__UnaryExpr__Group_0__2__Impl : ( '[' ) ;
    public final void rule__UnaryExpr__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:878:1: ( ( '[' ) )
            // InternalLTL.g:879:1: ( '[' )
            {
            // InternalLTL.g:879:1: ( '[' )
            // InternalLTL.g:880:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getLeftSquareBracketKeyword_0_2()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getLeftSquareBracketKeyword_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__2__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__3"
    // InternalLTL.g:889:1: rule__UnaryExpr__Group_0__3 : rule__UnaryExpr__Group_0__3__Impl rule__UnaryExpr__Group_0__4 ;
    public final void rule__UnaryExpr__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:893:1: ( rule__UnaryExpr__Group_0__3__Impl rule__UnaryExpr__Group_0__4 )
            // InternalLTL.g:894:2: rule__UnaryExpr__Group_0__3__Impl rule__UnaryExpr__Group_0__4
            {
            pushFollow(FOLLOW_7);
            rule__UnaryExpr__Group_0__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__3"


    // $ANTLR start "rule__UnaryExpr__Group_0__3__Impl"
    // InternalLTL.g:901:1: rule__UnaryExpr__Group_0__3__Impl : ( ( rule__UnaryExpr__LowerBoundAssignment_0_3 )? ) ;
    public final void rule__UnaryExpr__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:905:1: ( ( ( rule__UnaryExpr__LowerBoundAssignment_0_3 )? ) )
            // InternalLTL.g:906:1: ( ( rule__UnaryExpr__LowerBoundAssignment_0_3 )? )
            {
            // InternalLTL.g:906:1: ( ( rule__UnaryExpr__LowerBoundAssignment_0_3 )? )
            // InternalLTL.g:907:2: ( rule__UnaryExpr__LowerBoundAssignment_0_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getLowerBoundAssignment_0_3()); 
            }
            // InternalLTL.g:908:2: ( rule__UnaryExpr__LowerBoundAssignment_0_3 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_INT) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLTL.g:908:3: rule__UnaryExpr__LowerBoundAssignment_0_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__UnaryExpr__LowerBoundAssignment_0_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getLowerBoundAssignment_0_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__3__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__4"
    // InternalLTL.g:916:1: rule__UnaryExpr__Group_0__4 : rule__UnaryExpr__Group_0__4__Impl rule__UnaryExpr__Group_0__5 ;
    public final void rule__UnaryExpr__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:920:1: ( rule__UnaryExpr__Group_0__4__Impl rule__UnaryExpr__Group_0__5 )
            // InternalLTL.g:921:2: rule__UnaryExpr__Group_0__4__Impl rule__UnaryExpr__Group_0__5
            {
            pushFollow(FOLLOW_8);
            rule__UnaryExpr__Group_0__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__4"


    // $ANTLR start "rule__UnaryExpr__Group_0__4__Impl"
    // InternalLTL.g:928:1: rule__UnaryExpr__Group_0__4__Impl : ( ',' ) ;
    public final void rule__UnaryExpr__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:932:1: ( ( ',' ) )
            // InternalLTL.g:933:1: ( ',' )
            {
            // InternalLTL.g:933:1: ( ',' )
            // InternalLTL.g:934:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getCommaKeyword_0_4()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getCommaKeyword_0_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__4__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__5"
    // InternalLTL.g:943:1: rule__UnaryExpr__Group_0__5 : rule__UnaryExpr__Group_0__5__Impl rule__UnaryExpr__Group_0__6 ;
    public final void rule__UnaryExpr__Group_0__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:947:1: ( rule__UnaryExpr__Group_0__5__Impl rule__UnaryExpr__Group_0__6 )
            // InternalLTL.g:948:2: rule__UnaryExpr__Group_0__5__Impl rule__UnaryExpr__Group_0__6
            {
            pushFollow(FOLLOW_8);
            rule__UnaryExpr__Group_0__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__5"


    // $ANTLR start "rule__UnaryExpr__Group_0__5__Impl"
    // InternalLTL.g:955:1: rule__UnaryExpr__Group_0__5__Impl : ( ( rule__UnaryExpr__EndAssignment_0_5 )? ) ;
    public final void rule__UnaryExpr__Group_0__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:959:1: ( ( ( rule__UnaryExpr__EndAssignment_0_5 )? ) )
            // InternalLTL.g:960:1: ( ( rule__UnaryExpr__EndAssignment_0_5 )? )
            {
            // InternalLTL.g:960:1: ( ( rule__UnaryExpr__EndAssignment_0_5 )? )
            // InternalLTL.g:961:2: ( rule__UnaryExpr__EndAssignment_0_5 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getEndAssignment_0_5()); 
            }
            // InternalLTL.g:962:2: ( rule__UnaryExpr__EndAssignment_0_5 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==24) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLTL.g:962:3: rule__UnaryExpr__EndAssignment_0_5
                    {
                    pushFollow(FOLLOW_2);
                    rule__UnaryExpr__EndAssignment_0_5();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getEndAssignment_0_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__5__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__6"
    // InternalLTL.g:970:1: rule__UnaryExpr__Group_0__6 : rule__UnaryExpr__Group_0__6__Impl rule__UnaryExpr__Group_0__7 ;
    public final void rule__UnaryExpr__Group_0__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:974:1: ( rule__UnaryExpr__Group_0__6__Impl rule__UnaryExpr__Group_0__7 )
            // InternalLTL.g:975:2: rule__UnaryExpr__Group_0__6__Impl rule__UnaryExpr__Group_0__7
            {
            pushFollow(FOLLOW_8);
            rule__UnaryExpr__Group_0__6__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__7();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__6"


    // $ANTLR start "rule__UnaryExpr__Group_0__6__Impl"
    // InternalLTL.g:982:1: rule__UnaryExpr__Group_0__6__Impl : ( ( rule__UnaryExpr__UpperBoundAssignment_0_6 )? ) ;
    public final void rule__UnaryExpr__Group_0__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:986:1: ( ( ( rule__UnaryExpr__UpperBoundAssignment_0_6 )? ) )
            // InternalLTL.g:987:1: ( ( rule__UnaryExpr__UpperBoundAssignment_0_6 )? )
            {
            // InternalLTL.g:987:1: ( ( rule__UnaryExpr__UpperBoundAssignment_0_6 )? )
            // InternalLTL.g:988:2: ( rule__UnaryExpr__UpperBoundAssignment_0_6 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getUpperBoundAssignment_0_6()); 
            }
            // InternalLTL.g:989:2: ( rule__UnaryExpr__UpperBoundAssignment_0_6 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_INT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalLTL.g:989:3: rule__UnaryExpr__UpperBoundAssignment_0_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__UnaryExpr__UpperBoundAssignment_0_6();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getUpperBoundAssignment_0_6()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__6__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__7"
    // InternalLTL.g:997:1: rule__UnaryExpr__Group_0__7 : rule__UnaryExpr__Group_0__7__Impl rule__UnaryExpr__Group_0__8 ;
    public final void rule__UnaryExpr__Group_0__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1001:1: ( rule__UnaryExpr__Group_0__7__Impl rule__UnaryExpr__Group_0__8 )
            // InternalLTL.g:1002:2: rule__UnaryExpr__Group_0__7__Impl rule__UnaryExpr__Group_0__8
            {
            pushFollow(FOLLOW_5);
            rule__UnaryExpr__Group_0__7__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__8();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__7"


    // $ANTLR start "rule__UnaryExpr__Group_0__7__Impl"
    // InternalLTL.g:1009:1: rule__UnaryExpr__Group_0__7__Impl : ( ']' ) ;
    public final void rule__UnaryExpr__Group_0__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1013:1: ( ( ']' ) )
            // InternalLTL.g:1014:1: ( ']' )
            {
            // InternalLTL.g:1014:1: ( ']' )
            // InternalLTL.g:1015:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getRightSquareBracketKeyword_0_7()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getRightSquareBracketKeyword_0_7()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__7__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_0__8"
    // InternalLTL.g:1024:1: rule__UnaryExpr__Group_0__8 : rule__UnaryExpr__Group_0__8__Impl ;
    public final void rule__UnaryExpr__Group_0__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1028:1: ( rule__UnaryExpr__Group_0__8__Impl )
            // InternalLTL.g:1029:2: rule__UnaryExpr__Group_0__8__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_0__8__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__8"


    // $ANTLR start "rule__UnaryExpr__Group_0__8__Impl"
    // InternalLTL.g:1035:1: rule__UnaryExpr__Group_0__8__Impl : ( ( rule__UnaryExpr__ExprAssignment_0_8 ) ) ;
    public final void rule__UnaryExpr__Group_0__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1039:1: ( ( ( rule__UnaryExpr__ExprAssignment_0_8 ) ) )
            // InternalLTL.g:1040:1: ( ( rule__UnaryExpr__ExprAssignment_0_8 ) )
            {
            // InternalLTL.g:1040:1: ( ( rule__UnaryExpr__ExprAssignment_0_8 ) )
            // InternalLTL.g:1041:2: ( rule__UnaryExpr__ExprAssignment_0_8 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getExprAssignment_0_8()); 
            }
            // InternalLTL.g:1042:2: ( rule__UnaryExpr__ExprAssignment_0_8 )
            // InternalLTL.g:1042:3: rule__UnaryExpr__ExprAssignment_0_8
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__ExprAssignment_0_8();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getExprAssignment_0_8()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_0__8__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_1__0"
    // InternalLTL.g:1051:1: rule__UnaryExpr__Group_1__0 : rule__UnaryExpr__Group_1__0__Impl rule__UnaryExpr__Group_1__1 ;
    public final void rule__UnaryExpr__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1055:1: ( rule__UnaryExpr__Group_1__0__Impl rule__UnaryExpr__Group_1__1 )
            // InternalLTL.g:1056:2: rule__UnaryExpr__Group_1__0__Impl rule__UnaryExpr__Group_1__1
            {
            pushFollow(FOLLOW_12);
            rule__UnaryExpr__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_1__0"


    // $ANTLR start "rule__UnaryExpr__Group_1__0__Impl"
    // InternalLTL.g:1063:1: rule__UnaryExpr__Group_1__0__Impl : ( () ) ;
    public final void rule__UnaryExpr__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1067:1: ( ( () ) )
            // InternalLTL.g:1068:1: ( () )
            {
            // InternalLTL.g:1068:1: ( () )
            // InternalLTL.g:1069:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getUnaryExprAction_1_0()); 
            }
            // InternalLTL.g:1070:2: ()
            // InternalLTL.g:1070:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getUnaryExprAction_1_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_1__0__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_1__1"
    // InternalLTL.g:1078:1: rule__UnaryExpr__Group_1__1 : rule__UnaryExpr__Group_1__1__Impl rule__UnaryExpr__Group_1__2 ;
    public final void rule__UnaryExpr__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1082:1: ( rule__UnaryExpr__Group_1__1__Impl rule__UnaryExpr__Group_1__2 )
            // InternalLTL.g:1083:2: rule__UnaryExpr__Group_1__1__Impl rule__UnaryExpr__Group_1__2
            {
            pushFollow(FOLLOW_5);
            rule__UnaryExpr__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_1__1"


    // $ANTLR start "rule__UnaryExpr__Group_1__1__Impl"
    // InternalLTL.g:1090:1: rule__UnaryExpr__Group_1__1__Impl : ( ( rule__UnaryExpr__OpAssignment_1_1 ) ) ;
    public final void rule__UnaryExpr__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1094:1: ( ( ( rule__UnaryExpr__OpAssignment_1_1 ) ) )
            // InternalLTL.g:1095:1: ( ( rule__UnaryExpr__OpAssignment_1_1 ) )
            {
            // InternalLTL.g:1095:1: ( ( rule__UnaryExpr__OpAssignment_1_1 ) )
            // InternalLTL.g:1096:2: ( rule__UnaryExpr__OpAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getOpAssignment_1_1()); 
            }
            // InternalLTL.g:1097:2: ( rule__UnaryExpr__OpAssignment_1_1 )
            // InternalLTL.g:1097:3: rule__UnaryExpr__OpAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__OpAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getOpAssignment_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_1__1__Impl"


    // $ANTLR start "rule__UnaryExpr__Group_1__2"
    // InternalLTL.g:1105:1: rule__UnaryExpr__Group_1__2 : rule__UnaryExpr__Group_1__2__Impl ;
    public final void rule__UnaryExpr__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1109:1: ( rule__UnaryExpr__Group_1__2__Impl )
            // InternalLTL.g:1110:2: rule__UnaryExpr__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_1__2"


    // $ANTLR start "rule__UnaryExpr__Group_1__2__Impl"
    // InternalLTL.g:1116:1: rule__UnaryExpr__Group_1__2__Impl : ( ( rule__UnaryExpr__ExprAssignment_1_2 ) ) ;
    public final void rule__UnaryExpr__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1120:1: ( ( ( rule__UnaryExpr__ExprAssignment_1_2 ) ) )
            // InternalLTL.g:1121:1: ( ( rule__UnaryExpr__ExprAssignment_1_2 ) )
            {
            // InternalLTL.g:1121:1: ( ( rule__UnaryExpr__ExprAssignment_1_2 ) )
            // InternalLTL.g:1122:2: ( rule__UnaryExpr__ExprAssignment_1_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getExprAssignment_1_2()); 
            }
            // InternalLTL.g:1123:2: ( rule__UnaryExpr__ExprAssignment_1_2 )
            // InternalLTL.g:1123:3: rule__UnaryExpr__ExprAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__ExprAssignment_1_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getExprAssignment_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__Group_1__2__Impl"


    // $ANTLR start "rule__Literal__Group_0__0"
    // InternalLTL.g:1132:1: rule__Literal__Group_0__0 : rule__Literal__Group_0__0__Impl rule__Literal__Group_0__1 ;
    public final void rule__Literal__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1136:1: ( rule__Literal__Group_0__0__Impl rule__Literal__Group_0__1 )
            // InternalLTL.g:1137:2: rule__Literal__Group_0__0__Impl rule__Literal__Group_0__1
            {
            pushFollow(FOLLOW_13);
            rule__Literal__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__0"


    // $ANTLR start "rule__Literal__Group_0__0__Impl"
    // InternalLTL.g:1144:1: rule__Literal__Group_0__0__Impl : ( () ) ;
    public final void rule__Literal__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1148:1: ( ( () ) )
            // InternalLTL.g:1149:1: ( () )
            {
            // InternalLTL.g:1149:1: ( () )
            // InternalLTL.g:1150:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getIdFormulaAction_0_0()); 
            }
            // InternalLTL.g:1151:2: ()
            // InternalLTL.g:1151:3: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getIdFormulaAction_0_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__0__Impl"


    // $ANTLR start "rule__Literal__Group_0__1"
    // InternalLTL.g:1159:1: rule__Literal__Group_0__1 : rule__Literal__Group_0__1__Impl rule__Literal__Group_0__2 ;
    public final void rule__Literal__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1163:1: ( rule__Literal__Group_0__1__Impl rule__Literal__Group_0__2 )
            // InternalLTL.g:1164:2: rule__Literal__Group_0__1__Impl rule__Literal__Group_0__2
            {
            pushFollow(FOLLOW_14);
            rule__Literal__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__1"


    // $ANTLR start "rule__Literal__Group_0__1__Impl"
    // InternalLTL.g:1171:1: rule__Literal__Group_0__1__Impl : ( ( rule__Literal__VarNameAssignment_0_1 ) ) ;
    public final void rule__Literal__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1175:1: ( ( ( rule__Literal__VarNameAssignment_0_1 ) ) )
            // InternalLTL.g:1176:1: ( ( rule__Literal__VarNameAssignment_0_1 ) )
            {
            // InternalLTL.g:1176:1: ( ( rule__Literal__VarNameAssignment_0_1 ) )
            // InternalLTL.g:1177:2: ( rule__Literal__VarNameAssignment_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getVarNameAssignment_0_1()); 
            }
            // InternalLTL.g:1178:2: ( rule__Literal__VarNameAssignment_0_1 )
            // InternalLTL.g:1178:3: rule__Literal__VarNameAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Literal__VarNameAssignment_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getVarNameAssignment_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__1__Impl"


    // $ANTLR start "rule__Literal__Group_0__2"
    // InternalLTL.g:1186:1: rule__Literal__Group_0__2 : rule__Literal__Group_0__2__Impl rule__Literal__Group_0__3 ;
    public final void rule__Literal__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1190:1: ( rule__Literal__Group_0__2__Impl rule__Literal__Group_0__3 )
            // InternalLTL.g:1191:2: rule__Literal__Group_0__2__Impl rule__Literal__Group_0__3
            {
            pushFollow(FOLLOW_14);
            rule__Literal__Group_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__2"


    // $ANTLR start "rule__Literal__Group_0__2__Impl"
    // InternalLTL.g:1198:1: rule__Literal__Group_0__2__Impl : ( ( rule__Literal__Group_0_2__0 )? ) ;
    public final void rule__Literal__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1202:1: ( ( ( rule__Literal__Group_0_2__0 )? ) )
            // InternalLTL.g:1203:1: ( ( rule__Literal__Group_0_2__0 )? )
            {
            // InternalLTL.g:1203:1: ( ( rule__Literal__Group_0_2__0 )? )
            // InternalLTL.g:1204:2: ( rule__Literal__Group_0_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getGroup_0_2()); 
            }
            // InternalLTL.g:1205:2: ( rule__Literal__Group_0_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==19) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalLTL.g:1205:3: rule__Literal__Group_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Literal__Group_0_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getGroup_0_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__2__Impl"


    // $ANTLR start "rule__Literal__Group_0__3"
    // InternalLTL.g:1213:1: rule__Literal__Group_0__3 : rule__Literal__Group_0__3__Impl ;
    public final void rule__Literal__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1217:1: ( rule__Literal__Group_0__3__Impl )
            // InternalLTL.g:1218:2: rule__Literal__Group_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__3"


    // $ANTLR start "rule__Literal__Group_0__3__Impl"
    // InternalLTL.g:1224:1: rule__Literal__Group_0__3__Impl : ( ( rule__Literal__Group_0_3__0 )? ) ;
    public final void rule__Literal__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1228:1: ( ( ( rule__Literal__Group_0_3__0 )? ) )
            // InternalLTL.g:1229:1: ( ( rule__Literal__Group_0_3__0 )? )
            {
            // InternalLTL.g:1229:1: ( ( rule__Literal__Group_0_3__0 )? )
            // InternalLTL.g:1230:2: ( rule__Literal__Group_0_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getGroup_0_3()); 
            }
            // InternalLTL.g:1231:2: ( rule__Literal__Group_0_3__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==20) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalLTL.g:1231:3: rule__Literal__Group_0_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Literal__Group_0_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getGroup_0_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0__3__Impl"


    // $ANTLR start "rule__Literal__Group_0_2__0"
    // InternalLTL.g:1240:1: rule__Literal__Group_0_2__0 : rule__Literal__Group_0_2__0__Impl rule__Literal__Group_0_2__1 ;
    public final void rule__Literal__Group_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1244:1: ( rule__Literal__Group_0_2__0__Impl rule__Literal__Group_0_2__1 )
            // InternalLTL.g:1245:2: rule__Literal__Group_0_2__0__Impl rule__Literal__Group_0_2__1
            {
            pushFollow(FOLLOW_15);
            rule__Literal__Group_0_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_2__0"


    // $ANTLR start "rule__Literal__Group_0_2__0__Impl"
    // InternalLTL.g:1252:1: rule__Literal__Group_0_2__0__Impl : ( 'lower=' ) ;
    public final void rule__Literal__Group_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1256:1: ( ( 'lower=' ) )
            // InternalLTL.g:1257:1: ( 'lower=' )
            {
            // InternalLTL.g:1257:1: ( 'lower=' )
            // InternalLTL.g:1258:2: 'lower='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getLowerKeyword_0_2_0()); 
            }
            match(input,19,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getLowerKeyword_0_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_2__0__Impl"


    // $ANTLR start "rule__Literal__Group_0_2__1"
    // InternalLTL.g:1267:1: rule__Literal__Group_0_2__1 : rule__Literal__Group_0_2__1__Impl ;
    public final void rule__Literal__Group_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1271:1: ( rule__Literal__Group_0_2__1__Impl )
            // InternalLTL.g:1272:2: rule__Literal__Group_0_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_2__1"


    // $ANTLR start "rule__Literal__Group_0_2__1__Impl"
    // InternalLTL.g:1278:1: rule__Literal__Group_0_2__1__Impl : ( ( rule__Literal__LowerBoundAssignment_0_2_1 ) ) ;
    public final void rule__Literal__Group_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1282:1: ( ( ( rule__Literal__LowerBoundAssignment_0_2_1 ) ) )
            // InternalLTL.g:1283:1: ( ( rule__Literal__LowerBoundAssignment_0_2_1 ) )
            {
            // InternalLTL.g:1283:1: ( ( rule__Literal__LowerBoundAssignment_0_2_1 ) )
            // InternalLTL.g:1284:2: ( rule__Literal__LowerBoundAssignment_0_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getLowerBoundAssignment_0_2_1()); 
            }
            // InternalLTL.g:1285:2: ( rule__Literal__LowerBoundAssignment_0_2_1 )
            // InternalLTL.g:1285:3: rule__Literal__LowerBoundAssignment_0_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Literal__LowerBoundAssignment_0_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getLowerBoundAssignment_0_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_2__1__Impl"


    // $ANTLR start "rule__Literal__Group_0_3__0"
    // InternalLTL.g:1294:1: rule__Literal__Group_0_3__0 : rule__Literal__Group_0_3__0__Impl rule__Literal__Group_0_3__1 ;
    public final void rule__Literal__Group_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1298:1: ( rule__Literal__Group_0_3__0__Impl rule__Literal__Group_0_3__1 )
            // InternalLTL.g:1299:2: rule__Literal__Group_0_3__0__Impl rule__Literal__Group_0_3__1
            {
            pushFollow(FOLLOW_15);
            rule__Literal__Group_0_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_3__0"


    // $ANTLR start "rule__Literal__Group_0_3__0__Impl"
    // InternalLTL.g:1306:1: rule__Literal__Group_0_3__0__Impl : ( 'upper=' ) ;
    public final void rule__Literal__Group_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1310:1: ( ( 'upper=' ) )
            // InternalLTL.g:1311:1: ( 'upper=' )
            {
            // InternalLTL.g:1311:1: ( 'upper=' )
            // InternalLTL.g:1312:2: 'upper='
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getUpperKeyword_0_3_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getUpperKeyword_0_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_3__0__Impl"


    // $ANTLR start "rule__Literal__Group_0_3__1"
    // InternalLTL.g:1321:1: rule__Literal__Group_0_3__1 : rule__Literal__Group_0_3__1__Impl ;
    public final void rule__Literal__Group_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1325:1: ( rule__Literal__Group_0_3__1__Impl )
            // InternalLTL.g:1326:2: rule__Literal__Group_0_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group_0_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_3__1"


    // $ANTLR start "rule__Literal__Group_0_3__1__Impl"
    // InternalLTL.g:1332:1: rule__Literal__Group_0_3__1__Impl : ( ( rule__Literal__UpperBoundAssignment_0_3_1 ) ) ;
    public final void rule__Literal__Group_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1336:1: ( ( ( rule__Literal__UpperBoundAssignment_0_3_1 ) ) )
            // InternalLTL.g:1337:1: ( ( rule__Literal__UpperBoundAssignment_0_3_1 ) )
            {
            // InternalLTL.g:1337:1: ( ( rule__Literal__UpperBoundAssignment_0_3_1 ) )
            // InternalLTL.g:1338:2: ( rule__Literal__UpperBoundAssignment_0_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getUpperBoundAssignment_0_3_1()); 
            }
            // InternalLTL.g:1339:2: ( rule__Literal__UpperBoundAssignment_0_3_1 )
            // InternalLTL.g:1339:3: rule__Literal__UpperBoundAssignment_0_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Literal__UpperBoundAssignment_0_3_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getUpperBoundAssignment_0_3_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_0_3__1__Impl"


    // $ANTLR start "rule__Literal__Group_1__0"
    // InternalLTL.g:1348:1: rule__Literal__Group_1__0 : rule__Literal__Group_1__0__Impl rule__Literal__Group_1__1 ;
    public final void rule__Literal__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1352:1: ( rule__Literal__Group_1__0__Impl rule__Literal__Group_1__1 )
            // InternalLTL.g:1353:2: rule__Literal__Group_1__0__Impl rule__Literal__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__Literal__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_1__0"


    // $ANTLR start "rule__Literal__Group_1__0__Impl"
    // InternalLTL.g:1360:1: rule__Literal__Group_1__0__Impl : ( '(' ) ;
    public final void rule__Literal__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1364:1: ( ( '(' ) )
            // InternalLTL.g:1365:1: ( '(' )
            {
            // InternalLTL.g:1365:1: ( '(' )
            // InternalLTL.g:1366:2: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getLeftParenthesisKeyword_1_0()); 
            }
            match(input,21,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getLeftParenthesisKeyword_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_1__0__Impl"


    // $ANTLR start "rule__Literal__Group_1__1"
    // InternalLTL.g:1375:1: rule__Literal__Group_1__1 : rule__Literal__Group_1__1__Impl rule__Literal__Group_1__2 ;
    public final void rule__Literal__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1379:1: ( rule__Literal__Group_1__1__Impl rule__Literal__Group_1__2 )
            // InternalLTL.g:1380:2: rule__Literal__Group_1__1__Impl rule__Literal__Group_1__2
            {
            pushFollow(FOLLOW_16);
            rule__Literal__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Literal__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_1__1"


    // $ANTLR start "rule__Literal__Group_1__1__Impl"
    // InternalLTL.g:1387:1: rule__Literal__Group_1__1__Impl : ( ruleFormula ) ;
    public final void rule__Literal__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1391:1: ( ( ruleFormula ) )
            // InternalLTL.g:1392:1: ( ruleFormula )
            {
            // InternalLTL.g:1392:1: ( ruleFormula )
            // InternalLTL.g:1393:2: ruleFormula
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getFormulaParserRuleCall_1_1()); 
            }
            pushFollow(FOLLOW_2);
            ruleFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getFormulaParserRuleCall_1_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_1__1__Impl"


    // $ANTLR start "rule__Literal__Group_1__2"
    // InternalLTL.g:1402:1: rule__Literal__Group_1__2 : rule__Literal__Group_1__2__Impl ;
    public final void rule__Literal__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1406:1: ( rule__Literal__Group_1__2__Impl )
            // InternalLTL.g:1407:2: rule__Literal__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_1__2"


    // $ANTLR start "rule__Literal__Group_1__2__Impl"
    // InternalLTL.g:1413:1: rule__Literal__Group_1__2__Impl : ( ')' ) ;
    public final void rule__Literal__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1417:1: ( ( ')' ) )
            // InternalLTL.g:1418:1: ( ')' )
            {
            // InternalLTL.g:1418:1: ( ')' )
            // InternalLTL.g:1419:2: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getRightParenthesisKeyword_1_2()); 
            }
            match(input,22,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getRightParenthesisKeyword_1_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_1__2__Impl"


    // $ANTLR start "rule__UntilFormula__OpAssignment_1_0_0_1"
    // InternalLTL.g:1429:1: rule__UntilFormula__OpAssignment_1_0_0_1 : ( ( 'U' ) ) ;
    public final void rule__UntilFormula__OpAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1433:1: ( ( ( 'U' ) ) )
            // InternalLTL.g:1434:2: ( ( 'U' ) )
            {
            // InternalLTL.g:1434:2: ( ( 'U' ) )
            // InternalLTL.g:1435:3: ( 'U' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getOpUKeyword_1_0_0_1_0()); 
            }
            // InternalLTL.g:1436:3: ( 'U' )
            // InternalLTL.g:1437:4: 'U'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getOpUKeyword_1_0_0_1_0()); 
            }
            match(input,23,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getOpUKeyword_1_0_0_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getOpUKeyword_1_0_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__OpAssignment_1_0_0_1"


    // $ANTLR start "rule__UntilFormula__LowerBoundAssignment_1_0_0_3"
    // InternalLTL.g:1448:1: rule__UntilFormula__LowerBoundAssignment_1_0_0_3 : ( RULE_INT ) ;
    public final void rule__UntilFormula__LowerBoundAssignment_1_0_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1452:1: ( ( RULE_INT ) )
            // InternalLTL.g:1453:2: ( RULE_INT )
            {
            // InternalLTL.g:1453:2: ( RULE_INT )
            // InternalLTL.g:1454:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getLowerBoundINTTerminalRuleCall_1_0_0_3_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getLowerBoundINTTerminalRuleCall_1_0_0_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__LowerBoundAssignment_1_0_0_3"


    // $ANTLR start "rule__UntilFormula__EndAssignment_1_0_0_5"
    // InternalLTL.g:1463:1: rule__UntilFormula__EndAssignment_1_0_0_5 : ( ( 'end' ) ) ;
    public final void rule__UntilFormula__EndAssignment_1_0_0_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1467:1: ( ( ( 'end' ) ) )
            // InternalLTL.g:1468:2: ( ( 'end' ) )
            {
            // InternalLTL.g:1468:2: ( ( 'end' ) )
            // InternalLTL.g:1469:3: ( 'end' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getEndEndKeyword_1_0_0_5_0()); 
            }
            // InternalLTL.g:1470:3: ( 'end' )
            // InternalLTL.g:1471:4: 'end'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getEndEndKeyword_1_0_0_5_0()); 
            }
            match(input,24,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getEndEndKeyword_1_0_0_5_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getEndEndKeyword_1_0_0_5_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__EndAssignment_1_0_0_5"


    // $ANTLR start "rule__UntilFormula__UpperBoundAssignment_1_0_0_6"
    // InternalLTL.g:1482:1: rule__UntilFormula__UpperBoundAssignment_1_0_0_6 : ( RULE_INT ) ;
    public final void rule__UntilFormula__UpperBoundAssignment_1_0_0_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1486:1: ( ( RULE_INT ) )
            // InternalLTL.g:1487:2: ( RULE_INT )
            {
            // InternalLTL.g:1487:2: ( RULE_INT )
            // InternalLTL.g:1488:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getUpperBoundINTTerminalRuleCall_1_0_0_6_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getUpperBoundINTTerminalRuleCall_1_0_0_6_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__UpperBoundAssignment_1_0_0_6"


    // $ANTLR start "rule__UntilFormula__RightAssignment_1_1"
    // InternalLTL.g:1497:1: rule__UntilFormula__RightAssignment_1_1 : ( ruleConnectiveFormula ) ;
    public final void rule__UntilFormula__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1501:1: ( ( ruleConnectiveFormula ) )
            // InternalLTL.g:1502:2: ( ruleConnectiveFormula )
            {
            // InternalLTL.g:1502:2: ( ruleConnectiveFormula )
            // InternalLTL.g:1503:3: ruleConnectiveFormula
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUntilFormulaAccess().getRightConnectiveFormulaParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleConnectiveFormula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUntilFormulaAccess().getRightConnectiveFormulaParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UntilFormula__RightAssignment_1_1"


    // $ANTLR start "rule__ConnectiveFormula__OpAssignment_1_0_0_1"
    // InternalLTL.g:1512:1: rule__ConnectiveFormula__OpAssignment_1_0_0_1 : ( ( rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 ) ) ;
    public final void rule__ConnectiveFormula__OpAssignment_1_0_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1516:1: ( ( ( rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 ) ) )
            // InternalLTL.g:1517:2: ( ( rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 ) )
            {
            // InternalLTL.g:1517:2: ( ( rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 ) )
            // InternalLTL.g:1518:3: ( rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getOpAlternatives_1_0_0_1_0()); 
            }
            // InternalLTL.g:1519:3: ( rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0 )
            // InternalLTL.g:1519:4: rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ConnectiveFormula__OpAlternatives_1_0_0_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getOpAlternatives_1_0_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__OpAssignment_1_0_0_1"


    // $ANTLR start "rule__ConnectiveFormula__RightAssignment_1_1"
    // InternalLTL.g:1527:1: rule__ConnectiveFormula__RightAssignment_1_1 : ( ruleUnaryExpr ) ;
    public final void rule__ConnectiveFormula__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1531:1: ( ( ruleUnaryExpr ) )
            // InternalLTL.g:1532:2: ( ruleUnaryExpr )
            {
            // InternalLTL.g:1532:2: ( ruleUnaryExpr )
            // InternalLTL.g:1533:3: ruleUnaryExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getConnectiveFormulaAccess().getRightUnaryExprParserRuleCall_1_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleUnaryExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getConnectiveFormulaAccess().getRightUnaryExprParserRuleCall_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConnectiveFormula__RightAssignment_1_1"


    // $ANTLR start "rule__UnaryExpr__OpAssignment_0_1"
    // InternalLTL.g:1542:1: rule__UnaryExpr__OpAssignment_0_1 : ( ( rule__UnaryExpr__OpAlternatives_0_1_0 ) ) ;
    public final void rule__UnaryExpr__OpAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1546:1: ( ( ( rule__UnaryExpr__OpAlternatives_0_1_0 ) ) )
            // InternalLTL.g:1547:2: ( ( rule__UnaryExpr__OpAlternatives_0_1_0 ) )
            {
            // InternalLTL.g:1547:2: ( ( rule__UnaryExpr__OpAlternatives_0_1_0 ) )
            // InternalLTL.g:1548:3: ( rule__UnaryExpr__OpAlternatives_0_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getOpAlternatives_0_1_0()); 
            }
            // InternalLTL.g:1549:3: ( rule__UnaryExpr__OpAlternatives_0_1_0 )
            // InternalLTL.g:1549:4: rule__UnaryExpr__OpAlternatives_0_1_0
            {
            pushFollow(FOLLOW_2);
            rule__UnaryExpr__OpAlternatives_0_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getOpAlternatives_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__OpAssignment_0_1"


    // $ANTLR start "rule__UnaryExpr__LowerBoundAssignment_0_3"
    // InternalLTL.g:1557:1: rule__UnaryExpr__LowerBoundAssignment_0_3 : ( RULE_INT ) ;
    public final void rule__UnaryExpr__LowerBoundAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1561:1: ( ( RULE_INT ) )
            // InternalLTL.g:1562:2: ( RULE_INT )
            {
            // InternalLTL.g:1562:2: ( RULE_INT )
            // InternalLTL.g:1563:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getLowerBoundINTTerminalRuleCall_0_3_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getLowerBoundINTTerminalRuleCall_0_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__LowerBoundAssignment_0_3"


    // $ANTLR start "rule__UnaryExpr__EndAssignment_0_5"
    // InternalLTL.g:1572:1: rule__UnaryExpr__EndAssignment_0_5 : ( ( 'end' ) ) ;
    public final void rule__UnaryExpr__EndAssignment_0_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1576:1: ( ( ( 'end' ) ) )
            // InternalLTL.g:1577:2: ( ( 'end' ) )
            {
            // InternalLTL.g:1577:2: ( ( 'end' ) )
            // InternalLTL.g:1578:3: ( 'end' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getEndEndKeyword_0_5_0()); 
            }
            // InternalLTL.g:1579:3: ( 'end' )
            // InternalLTL.g:1580:4: 'end'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getEndEndKeyword_0_5_0()); 
            }
            match(input,24,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getEndEndKeyword_0_5_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getEndEndKeyword_0_5_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__EndAssignment_0_5"


    // $ANTLR start "rule__UnaryExpr__UpperBoundAssignment_0_6"
    // InternalLTL.g:1591:1: rule__UnaryExpr__UpperBoundAssignment_0_6 : ( RULE_INT ) ;
    public final void rule__UnaryExpr__UpperBoundAssignment_0_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1595:1: ( ( RULE_INT ) )
            // InternalLTL.g:1596:2: ( RULE_INT )
            {
            // InternalLTL.g:1596:2: ( RULE_INT )
            // InternalLTL.g:1597:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getUpperBoundINTTerminalRuleCall_0_6_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getUpperBoundINTTerminalRuleCall_0_6_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__UpperBoundAssignment_0_6"


    // $ANTLR start "rule__UnaryExpr__ExprAssignment_0_8"
    // InternalLTL.g:1606:1: rule__UnaryExpr__ExprAssignment_0_8 : ( ruleUnaryExpr ) ;
    public final void rule__UnaryExpr__ExprAssignment_0_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1610:1: ( ( ruleUnaryExpr ) )
            // InternalLTL.g:1611:2: ( ruleUnaryExpr )
            {
            // InternalLTL.g:1611:2: ( ruleUnaryExpr )
            // InternalLTL.g:1612:3: ruleUnaryExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getExprUnaryExprParserRuleCall_0_8_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleUnaryExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getExprUnaryExprParserRuleCall_0_8_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__ExprAssignment_0_8"


    // $ANTLR start "rule__UnaryExpr__OpAssignment_1_1"
    // InternalLTL.g:1621:1: rule__UnaryExpr__OpAssignment_1_1 : ( ( '-' ) ) ;
    public final void rule__UnaryExpr__OpAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1625:1: ( ( ( '-' ) ) )
            // InternalLTL.g:1626:2: ( ( '-' ) )
            {
            // InternalLTL.g:1626:2: ( ( '-' ) )
            // InternalLTL.g:1627:3: ( '-' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getOpHyphenMinusKeyword_1_1_0()); 
            }
            // InternalLTL.g:1628:3: ( '-' )
            // InternalLTL.g:1629:4: '-'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getOpHyphenMinusKeyword_1_1_0()); 
            }
            match(input,25,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getOpHyphenMinusKeyword_1_1_0()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getOpHyphenMinusKeyword_1_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__OpAssignment_1_1"


    // $ANTLR start "rule__UnaryExpr__ExprAssignment_1_2"
    // InternalLTL.g:1640:1: rule__UnaryExpr__ExprAssignment_1_2 : ( ruleUnaryExpr ) ;
    public final void rule__UnaryExpr__ExprAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1644:1: ( ( ruleUnaryExpr ) )
            // InternalLTL.g:1645:2: ( ruleUnaryExpr )
            {
            // InternalLTL.g:1645:2: ( ruleUnaryExpr )
            // InternalLTL.g:1646:3: ruleUnaryExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnaryExprAccess().getExprUnaryExprParserRuleCall_1_2_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleUnaryExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnaryExprAccess().getExprUnaryExprParserRuleCall_1_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__UnaryExpr__ExprAssignment_1_2"


    // $ANTLR start "rule__Literal__VarNameAssignment_0_1"
    // InternalLTL.g:1655:1: rule__Literal__VarNameAssignment_0_1 : ( RULE_VAR_NAME ) ;
    public final void rule__Literal__VarNameAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1659:1: ( ( RULE_VAR_NAME ) )
            // InternalLTL.g:1660:2: ( RULE_VAR_NAME )
            {
            // InternalLTL.g:1660:2: ( RULE_VAR_NAME )
            // InternalLTL.g:1661:3: RULE_VAR_NAME
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getVarNameVAR_NAMETerminalRuleCall_0_1_0()); 
            }
            match(input,RULE_VAR_NAME,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getVarNameVAR_NAMETerminalRuleCall_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__VarNameAssignment_0_1"


    // $ANTLR start "rule__Literal__LowerBoundAssignment_0_2_1"
    // InternalLTL.g:1670:1: rule__Literal__LowerBoundAssignment_0_2_1 : ( RULE_INT ) ;
    public final void rule__Literal__LowerBoundAssignment_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1674:1: ( ( RULE_INT ) )
            // InternalLTL.g:1675:2: ( RULE_INT )
            {
            // InternalLTL.g:1675:2: ( RULE_INT )
            // InternalLTL.g:1676:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getLowerBoundINTTerminalRuleCall_0_2_1_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getLowerBoundINTTerminalRuleCall_0_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__LowerBoundAssignment_0_2_1"


    // $ANTLR start "rule__Literal__UpperBoundAssignment_0_3_1"
    // InternalLTL.g:1685:1: rule__Literal__UpperBoundAssignment_0_3_1 : ( RULE_INT ) ;
    public final void rule__Literal__UpperBoundAssignment_0_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalLTL.g:1689:1: ( ( RULE_INT ) )
            // InternalLTL.g:1690:2: ( RULE_INT )
            {
            // InternalLTL.g:1690:2: ( RULE_INT )
            // InternalLTL.g:1691:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLiteralAccess().getUpperBoundINTTerminalRuleCall_0_3_1_0()); 
            }
            match(input,RULE_INT,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLiteralAccess().getUpperBoundINTTerminalRuleCall_0_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__UpperBoundAssignment_0_3_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000000220C020L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000001040010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000003002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});

}