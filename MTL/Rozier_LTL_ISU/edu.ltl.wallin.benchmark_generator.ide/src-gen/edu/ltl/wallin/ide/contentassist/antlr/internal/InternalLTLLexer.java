package edu.ltl.wallin.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLTLLexer extends Lexer {
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

    public InternalLTLLexer() {;} 
    public InternalLTLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalLTLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalLTL.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:11:7: ( '&' )
            // InternalLTL.g:11:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:12:7: ( '|' )
            // InternalLTL.g:12:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:13:7: ( 'F' )
            // InternalLTL.g:13:9: 'F'
            {
            match('F'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:14:7: ( 'G' )
            // InternalLTL.g:14:9: 'G'
            {
            match('G'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:15:7: ( '[' )
            // InternalLTL.g:15:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:16:7: ( ',' )
            // InternalLTL.g:16:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:17:7: ( ']' )
            // InternalLTL.g:17:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:18:7: ( 'lower=' )
            // InternalLTL.g:18:9: 'lower='
            {
            match("lower="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:19:7: ( 'upper=' )
            // InternalLTL.g:19:9: 'upper='
            {
            match("upper="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:20:7: ( '(' )
            // InternalLTL.g:20:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:21:7: ( ')' )
            // InternalLTL.g:21:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:22:7: ( 'U' )
            // InternalLTL.g:22:9: 'U'
            {
            match('U'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:23:7: ( 'end' )
            // InternalLTL.g:23:9: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:24:7: ( '-' )
            // InternalLTL.g:24:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "RULE_VAR_NAME"
    public final void mRULE_VAR_NAME() throws RecognitionException {
        try {
            int _type = RULE_VAR_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1700:15: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' )* )
            // InternalLTL.g:1700:17: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            {
            // InternalLTL.g:1700:17: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalLTL.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_VAR_NAME"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1702:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalLTL.g:1702:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalLTL.g:1702:11: ( '^' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='^') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalLTL.g:1702:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalLTL.g:1702:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalLTL.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1704:10: ( ( '0' .. '9' )+ )
            // InternalLTL.g:1704:12: ( '0' .. '9' )+
            {
            // InternalLTL.g:1704:12: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalLTL.g:1704:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1706:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalLTL.g:1706:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalLTL.g:1706:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\'') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalLTL.g:1706:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalLTL.g:1706:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalLTL.g:1706:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalLTL.g:1706:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalLTL.g:1706:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalLTL.g:1706:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalLTL.g:1706:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalLTL.g:1706:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1708:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalLTL.g:1708:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalLTL.g:1708:24: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalLTL.g:1708:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1710:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalLTL.g:1710:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalLTL.g:1710:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalLTL.g:1710:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // InternalLTL.g:1710:40: ( ( '\\r' )? '\\n' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLTL.g:1710:41: ( '\\r' )? '\\n'
                    {
                    // InternalLTL.g:1710:41: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalLTL.g:1710:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1712:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalLTL.g:1712:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalLTL.g:1712:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalLTL.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalLTL.g:1714:16: ( . )
            // InternalLTL.g:1714:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalLTL.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | RULE_VAR_NAME | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt13=22;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // InternalLTL.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // InternalLTL.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // InternalLTL.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // InternalLTL.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // InternalLTL.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // InternalLTL.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // InternalLTL.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // InternalLTL.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // InternalLTL.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // InternalLTL.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // InternalLTL.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // InternalLTL.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // InternalLTL.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // InternalLTL.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // InternalLTL.g:1:94: RULE_VAR_NAME
                {
                mRULE_VAR_NAME(); 

                }
                break;
            case 16 :
                // InternalLTL.g:1:108: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 17 :
                // InternalLTL.g:1:116: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 18 :
                // InternalLTL.g:1:125: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 19 :
                // InternalLTL.g:1:137: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 20 :
                // InternalLTL.g:1:153: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 21 :
                // InternalLTL.g:1:169: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 22 :
                // InternalLTL.g:1:177: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\20\2\uffff\1\32\1\35\3\uffff\2\20\2\uffff\1\45\1\20\1\uffff\1\20\1\uffff\1\27\1\uffff\3\27\5\uffff\1\20\5\uffff\2\20\3\uffff\1\20\6\uffff\2\20\1\62\2\20\1\uffff\2\20\2\uffff";
    static final String DFA13_eofS =
        "\67\uffff";
    static final String DFA13_minS =
        "\1\0\2\uffff\2\60\3\uffff\2\60\2\uffff\2\60\1\uffff\1\60\1\uffff\1\101\1\uffff\2\0\1\52\5\uffff\1\60\5\uffff\2\60\3\uffff\1\60\6\uffff\5\60\1\uffff\2\60\2\uffff";
    static final String DFA13_maxS =
        "\1\uffff\2\uffff\2\172\3\uffff\2\172\2\uffff\2\172\1\uffff\1\172\1\uffff\1\172\1\uffff\2\uffff\1\57\5\uffff\1\172\5\uffff\2\172\3\uffff\1\172\6\uffff\5\172\1\uffff\2\172\2\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\1\1\2\2\uffff\1\5\1\6\1\7\2\uffff\1\12\1\13\2\uffff\1\16\1\uffff\1\17\1\uffff\1\21\3\uffff\1\25\1\26\1\1\1\2\1\3\1\uffff\1\20\1\4\1\5\1\6\1\7\2\uffff\1\12\1\13\1\14\1\uffff\1\16\1\21\1\22\1\23\1\24\1\25\5\uffff\1\15\2\uffff\1\10\1\11";
    static final String DFA13_specialS =
        "\1\2\22\uffff\1\0\1\1\42\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\27\2\26\2\27\1\26\22\27\1\26\1\27\1\23\3\27\1\1\1\24\1\12\1\13\2\27\1\6\1\16\1\27\1\25\12\22\7\27\5\17\1\3\1\4\15\17\1\14\5\17\1\5\1\27\1\7\1\21\1\17\1\27\4\17\1\15\6\17\1\10\10\17\1\11\5\17\1\27\1\2\uff83\27",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\16\33\1\41\13\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\17\33\1\42\12\33",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\15\33\1\46\14\33",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "\32\34\4\uffff\1\34\1\uffff\32\34",
            "",
            "\0\51",
            "\0\51",
            "\1\52\4\uffff\1\53",
            "",
            "",
            "",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "",
            "",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\26\33\1\55\3\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\17\33\1\56\12\33",
            "",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\3\33\1\57\26\33",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\4\33\1\60\25\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\4\33\1\61\25\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\21\33\1\63\10\33",
            "\12\34\7\uffff\32\33\4\uffff\1\33\1\uffff\21\33\1\64\10\33",
            "",
            "\12\34\3\uffff\1\65\3\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\34\3\uffff\1\66\3\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | RULE_VAR_NAME | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_19 = input.LA(1);

                        s = -1;
                        if ( ((LA13_19>='\u0000' && LA13_19<='\uFFFF')) ) {s = 41;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_20 = input.LA(1);

                        s = -1;
                        if ( ((LA13_20>='\u0000' && LA13_20<='\uFFFF')) ) {s = 41;}

                        else s = 23;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='&') ) {s = 1;}

                        else if ( (LA13_0=='|') ) {s = 2;}

                        else if ( (LA13_0=='F') ) {s = 3;}

                        else if ( (LA13_0=='G') ) {s = 4;}

                        else if ( (LA13_0=='[') ) {s = 5;}

                        else if ( (LA13_0==',') ) {s = 6;}

                        else if ( (LA13_0==']') ) {s = 7;}

                        else if ( (LA13_0=='l') ) {s = 8;}

                        else if ( (LA13_0=='u') ) {s = 9;}

                        else if ( (LA13_0=='(') ) {s = 10;}

                        else if ( (LA13_0==')') ) {s = 11;}

                        else if ( (LA13_0=='U') ) {s = 12;}

                        else if ( (LA13_0=='e') ) {s = 13;}

                        else if ( (LA13_0=='-') ) {s = 14;}

                        else if ( ((LA13_0>='A' && LA13_0<='E')||(LA13_0>='H' && LA13_0<='T')||(LA13_0>='V' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='d')||(LA13_0>='f' && LA13_0<='k')||(LA13_0>='m' && LA13_0<='t')||(LA13_0>='v' && LA13_0<='z')) ) {s = 15;}

                        else if ( (LA13_0=='^') ) {s = 17;}

                        else if ( ((LA13_0>='0' && LA13_0<='9')) ) {s = 18;}

                        else if ( (LA13_0=='\"') ) {s = 19;}

                        else if ( (LA13_0=='\'') ) {s = 20;}

                        else if ( (LA13_0=='/') ) {s = 21;}

                        else if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {s = 22;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='\b')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\u001F')||LA13_0=='!'||(LA13_0>='#' && LA13_0<='%')||(LA13_0>='*' && LA13_0<='+')||LA13_0=='.'||(LA13_0>=':' && LA13_0<='@')||LA13_0=='\\'||LA13_0=='`'||LA13_0=='{'||(LA13_0>='}' && LA13_0<='\uFFFF')) ) {s = 23;}

                        else s = 16;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}