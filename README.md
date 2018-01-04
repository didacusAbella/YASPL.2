# YASPL.2

It is a programming language designed for the Compiler course from University of Salerno (Italy). It's a toy procedural languages that
compiles into llvm with the following phases:

1. Yaspl.2 -> C Language
2. C source code -> LLVM with clang

## Lexical Specification

Here we are the formal lexical specification of Yaspl Language 

|Token |Lexeme|
|------|------|
|HEAD  |head  |
|START |start |
|SEMI  |;     |
|INT   |int   |
|BOOL  |bool  |
|DOUBLE|double|
|NAME  |jletter (jletter | jdigit)*|
|COMMA |,|
|DEF |def|
|LPAR |(|
|RPAR |)|
|COLON |:|
|LGPAR |{|
|RGPAR |}|
|READ |<-|
|WRITE |->|
|PLUS |+|
|MINUS |-|
|TIMES |*|
|DIV |/|
|INT_CONST |pattern per interi|
|DOUBLE_CONST |pattern per double|
|STRING_CONST |pattern per stringhe|
|TRUE |true| 
|FALSE |false|
|ASSIGN |=|
|IF |if|
|THEN |then|
|WHILE |while|
|DO |do|
|ELSE |else|
|GT |>|
|GE |>=|
|LT |<|
|LE |<=|
|EQ |==|
|NOT |not|
|AND |&&|
|OR |&#124;|
|UMINUS |-|

## Grammar Specification
This is the syntax specification of the language. These are the set of production for YASPL.2 language. On the left of each arrow are the heads 
of productions and on the right there are the bodied of production. The bodies can have one or more production to be applied separated by
*|* symbol. ε symbol denotates an empty production.

**Programma** → HEAD Decls START Statements

**Decls** → Var_decl Decls | Def_decl Decls| ε

**Statements** → Stat Statements | Stat

**Var_decl** → Type Vars SEMI

**Type** → INT | BOOL | DOUBLE

**Vars** → NAME COMMA Vars | NAME

**Types** → Type COMMA Types| Type

**Def_decl** → DEF NAME LPAR Var_decls RPAR COLON Par_decls Body

**Var_decls** → Var_decl Var_decls| ε

**Par_decls** → Var_decl Par_decls| Var_decl

**Body** → LGPAR Var_decls Statements RGPAR SEMI

**Comp_stat** → LGPAR Statements RGPAR

**Stat** → Comp_stat | Simple_stat

**Simple_stat** → Vars READ Types SEMI | Out_values WRITE SEMI | NAME ASSIGN Expr SEMI | NAME LPAR Exprs COLON Vars RPAR SEMI
                  | IF LPAR Bool_expr RPAR THEN Comp_stat ELSE Comp_stat SEMI | IF LPAR Bool_expr RPAR THEN Comp_stat SEMI
                  | WHILE LPAR Bool_expr RPAR DO Comp_stat SEMI
                  
**Out_values** → Expr COMMA Out_values | STRING_CONST COMMA Out_values | Expr | STRING_CONST

**Exprs** → Expr COMMA Exprs | Expr

**Expr** → INT_CONST | DOUBLE_CONST | Expr Arith_op Expr | NAME | UMINUS Expr| LPAR Expr RPAR

**Arith_op** → PLUS | MINUS | TIMES | DIV

**Bool_expr** → TRUE| FALSE| Bool_expr Bool_op Bool_expr| NOT Bool_expr| Expr Rel_op Expr| LPAR Bool_Expr RPAR

**Bool_op** → AND | OR

**Rel_op** → GT | GE | LT | LE | EQ

## Tecnhologies
1. Java 
2. JFlex
3. Java Cup
