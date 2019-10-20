package com.didacusabella.yaspl.dist;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.Symbol;
import java.io.InputStreamReader;
import com.didacusabella.yaspl.lexical.StringTable;
import java.util.HashMap;
%%

%class Lexer
%public
%unicode
%line
%column
%cup

%eofval{
    return symbol("EOF", sym.EOF);
%eofval}

%{
    private StringTable table;
    private StringBuffer sb = new StringBuffer();
    private ComplexSymbolFactory symbolFactory;

    public Lexer(ComplexSymbolFactory sf, java.io.InputStream is, StringTable table){
      this(new InputStreamReader(is));
      this.symbolFactory = sf;
      this.table = table;
    }


    public Symbol symbol(String name, int code){
		return symbolFactory.newSymbol(name, code,
						new Location(yyline+1,yycolumn+1 - yylength()),
						new Location(yyline+1,yycolumn+1));
    }

    public Symbol symbol(String name, int code, Object value){
        this.table.install(value.toString());
        return symbolFactory.newSymbol(name, code,
    					new Location(yyline+1, yycolumn+1),
    					new Location(yyline+1, yycolumn+yylength()), value);
    }

    protected void emit_warning(String message){
    	System.out.println("scanner warning: " + message + " at : 2 "+
    			(yyline+1) + " " + (yycolumn+1));
    }

    protected void emit_error(String message){
    	System.out.println("scanner error: " + message + " at : 2" +
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}

Newline    = \r | \n | \r\n
Whitespace = [ \t\f] | {Newline}
DecLiteral = 0 | [1-9][0-9]*
DoubleLiteral = (0 | [1-9][0-9]*)\.[0-9]+
StringLiteral = [^\r\n\"\\]


/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}
TraditionalComment = "/*" {CommentContent} \*+ "/"
EndOfLineComment = "//" [^\r\n]* {Newline}?
CommentContent = ( [^*] | \*+[^*/] )*

Identifier = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*

%state STRING

%%

<YYINITIAL> {

  {Whitespace} {/*ignore*/ }
  {Comment} {/*ignore*/ }
  "head" { return symbol("HEAD", sym.HEAD); }
  "start" { return symbol("START", sym.START); }
  ";" { return symbol("SEMI", sym.SEMI); }
  "int" { return symbol("INT", sym.INT); }
  "bool" { return symbol("BOOL", sym.BOOL); }
  "double" { return symbol("DOUBLE", sym.DOUBLE); }
  "string" { return symbol("STRING", sym.STRING); }
  "," {return symbol("COMMA", sym.COMMA); }
  "def" {return symbol("DEF", sym.DEF); }
  "(" { return symbol("LPAR", sym.LPAR); }
  ")" { return symbol("RPAR", sym.RPAR); }
  ":" { return symbol("COLON", sym.COLON); }
  "{" { return symbol("LGPAR", sym.LGPAR); }
  "}" { return symbol("RGPAR", sym.RGPAR); }
  "<-" { return symbol("READ", sym.READ); }
  "->" { return symbol("WRITE", sym.WRITE); }
  "+" {return symbol("PLUS", sym.PLUS); }
  "-" { return symbol("MINUS", sym.MINUS); }
  "*" { return symbol("TIMES", sym.TIMES); }
  "/" { return symbol("DIV", sym.DIV); }
  "true" { return symbol("TRUE", sym.TRUE, Boolean.parseBoolean(yytext())); }
  "false" { return symbol("FALSE", sym.FALSE, Boolean.parseBoolean(yytext())); }
  "=" { return symbol("ASSIGN", sym.ASSIGN); }
  "if" { return symbol("IF", sym.IF); }
  "else" { return symbol("ELSE", sym.ELSE); }
  "then" { return symbol("THEN", sym.THEN); }
  "while" { return symbol("WHILE", sym.WHILE); }
  "do" { return symbol("DO", sym.DO); }
  ">" { return symbol("GT", sym.GT); }
  ">=" { return symbol("GE", sym.GE); }
  "<" { return symbol("LT", sym.LT); }
  "<=" { return symbol("LE", sym.LE); }
  "==" { return symbol("EQ", sym.EQ); }
  "not" { return symbol("NOT", sym.NOT); }
  "&&" { return symbol("AND", sym.AND); }
  "||" { return symbol("OR", sym.OR); }
  {Identifier} {return symbol("NAME", sym.NAME, yytext()); }
  {DoubleLiteral} { return symbol("DOUBLE_CONST", sym.DOUBLE_CONST, Double.parseDouble(yytext())); }
  {DecLiteral} { return symbol("INT_CONST", sym.INT_CONST, Integer.parseInt(yytext())); }
  \" { yybegin(STRING); sb.setLength(0); }
}

<STRING> {
\" { yybegin(YYINITIAL); return symbol("STRING_CONST", sym.STRING_CONST, sb.toString()); }
/* escape sequences */
  {StringLiteral}+ { sb.append( yytext()); }
  "\\b" { sb.append( '\b' ); }
  "\\t" { sb.append( '\t' ); }
  "\\n" { sb.append( '\n' ); }
  "\\f" { sb.append( '\f' ); }
  "\\r" { sb.append( '\r' ); }
  "\\\"" { sb.append( '\"' ); }
  "\\'" { sb.append( '\'' ); }
  "\\\\" { sb.append( '\\' ); }
  \\. { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
}



// error fallback
[^]          { emit_warning("Unrecognized character '" +yytext()+"' -- ignored"); }
