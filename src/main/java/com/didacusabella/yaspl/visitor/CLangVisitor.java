package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.semantic.SymbolKind;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.*;
import com.didacusabella.yaspl.type.PrimitiveType;
import com.didacusabella.yaspl.type.Type;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

/**
 *
 * @author didacus
 */
public class CLangVisitor implements Visitor<String, SymbolTable> {
  
  private final String root;

  public CLangVisitor(String root) {
    this.root = root;
  }
  
  @Override
  public String visit(Program program, SymbolTable arg) {
    arg.enterScope();
    String decl = this.beautify(program.getDeclarations(), new StringJoiner("\n"), arg);
    String sts = this.beautify(program.getStatements(), new StringJoiner("\n"), arg);
    arg.exitScope();
    return this.root.replace("$declarations$", decl).replace("$statements$", sts);
  }
  
  private String beautify(List<? extends AstNode> nodes, StringJoiner joiner, SymbolTable table){
    nodes.forEach(node -> joiner.add(node.accept(this, table)));
    return joiner.toString();
  }

  @Override
  public String visit(VariableDeclaration variableDeclaration, SymbolTable arg) {
    String type = variableDeclaration.getType().accept(this, arg);
    String varDecls = this.beautify(variableDeclaration.getVariables(), new StringJoiner(","), arg);
    return String.format("%s %s;", type, varDecls);
  }

  @Override
  public String visit(FunctionDeclaration functionDeclaration, SymbolTable arg) {
    String name = functionDeclaration.getName().accept(this, arg);
    StringJoiner arguments = new StringJoiner(",");
    arg.enterScope();
    functionDeclaration.getInputs().forEach(this.formatArg(arguments, arg));
    this.beautify(functionDeclaration.getOutputs(), arguments, arg);
    String body = functionDeclaration.getBody().accept(this, arg);
    arg.exitScope();
    return String.format("void %s(%s){\n%s\n}", name, arguments.toString(), body);
  }
  
  private Consumer<VariableDeclaration> formatArg(StringJoiner joiner, SymbolTable table){
    return new Consumer<VariableDeclaration>() {
      @Override
      public void accept(VariableDeclaration t) {
        String type = t.getType().accept(CLangVisitor.this, table);
        t.getVariables().forEach(v -> joiner.add(String.format("%s %s", type, 
                v.accept(CLangVisitor.this, table))));
      }
    };
  }

  @Override
  public String visit(Variable variable, SymbolTable arg) {
    SymbolKind kind = arg.lookup(variable.getValue()).get().getKind();
    if(kind == SymbolKind.OUTPUT)
      return "*".concat(variable.getValue());
    else
      return variable.getValue();
  }

  @Override
  public String visit(TypeDenoter type, SymbolTable arg) {
    return type.typeFactory().cType();
  }

  @Override
  public String visit(Identifier identifier, SymbolTable arg) {
    SymbolKind kind = arg.lookup(identifier.getValue()).get().getKind();
    if(kind == SymbolKind.OUTPUT)
      return "*".concat(identifier.getValue());
    else
      return identifier.getValue();
  }

  @Override
  public String visit(ParameterDeclaration parameterDeclaration, SymbolTable arg) {
    String type = parameterDeclaration.getType().accept(this, arg);
    StringJoiner joiner = new StringJoiner(",");
    parameterDeclaration.getVariables().forEach(v -> joiner
            .add(String.format("%s %s", type, v.accept(this, arg))));
    return joiner.toString();
  }

  @Override
  public String visit(Body body, SymbolTable arg) {
    StringJoiner bodyJoiner = new StringJoiner("\n");
    this.beautify(body.getVariableDeclarations(), bodyJoiner, arg);
    this.beautify(body.getStatements(), bodyJoiner, arg);
    return bodyJoiner.toString();
  }

  @Override
  public String visit(ReadStatement readStatement, SymbolTable arg) {
    StringJoiner typeJoiner = new StringJoiner(" ");
    readStatement.getTypes().forEach(t -> typeJoiner.add(this.formatType(t.typeFactory())));
    StringJoiner read = new StringJoiner(",");
    readStatement.getIdentifiers().forEach(i -> read.add("&".concat(i.accept(this, arg))));
    return String.format("scanf(\"%s\", %s);", typeJoiner.toString(), read.toString());
  }
  
  private String formatType(Type type){
    PrimitiveType pType = PrimitiveType.class.cast(type);
    switch(pType){
      case DOUBLE:
        return "%lf";
      case STRING:
        return "%s";
      default:
        return "%d";
    }
  }

  @Override
  public String visit(WriteStatement writeStatement, SymbolTable arg) {
    StringJoiner typeJoiner = new StringJoiner(" ");
    StringJoiner exprJoiner = new StringJoiner(",");
    writeStatement.getExpressions().forEach(e -> {
      typeJoiner.add(this.formatType(e.getType()));
      exprJoiner.add(e.accept(this, arg));
    });
    return String.format("printf(\"%s\", %s);", typeJoiner.toString(), exprJoiner.toString());
  }

  @Override
  public String visit(FunctionCall functionCall, SymbolTable arg) {
    StringJoiner funJoiner = new StringJoiner(",");
    functionCall.getInputs().forEach(i -> funJoiner.add(i.accept(this, arg)));
    functionCall.getOutputs().forEach(o -> funJoiner.add("&".concat(o.accept(this, arg))));
    String fName = functionCall.getName().accept(this, arg);
    return String.format("%s(%s);", fName, funJoiner.toString());
  }

  @Override
  public String visit(CompositeStatement compositeStatement, SymbolTable arg) {
    StringJoiner compJoiner = new StringJoiner("\n");
    this.beautify(compositeStatement.getStatements(), compJoiner, arg);
    return compJoiner.toString();
  }

  @Override
  public String visit(WhileStatement whileStatement, SymbolTable arg) {
    String condition = whileStatement.getCondition().accept(this, arg);
    arg.enterScope();
    String body = whileStatement.getBody().accept(this, arg);
    arg.exitScope();
    return String.format("while(%s){\n%s\n}", condition, body);
  }

  @Override
  public String visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    String condition = ifThenStatement.getIfCondition().accept(this, arg);
    String then = ifThenStatement.getThenStatement().accept(this, arg);
    return String.format("if(%s){\n%s\n}", condition, then);
  }

  @Override
  public String visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    String condition = ifThenElseStatement.getIfCondition().accept(this, arg);
    String then = ifThenElseStatement.getThenStatement().accept(this, arg);
    String els = ifThenElseStatement.getElseStatement().accept(this, arg);
    return String.format("if(%s){\n%s\n}\nelse{\n%s\n}", condition, then, els);
  }

  @Override
  public String visit(BinaryExpression binaryExpression, SymbolTable arg) {
    String left = binaryExpression.getLeftOperand().accept(this, arg);
    String right = binaryExpression.getRightOperand().accept(this, arg);
    String op = this.formatOp(binaryExpression.getOp());
    return String.format("%s %s %s;", left, op, right);
  }
  
  private String formatOp(String otherFormat){
    switch(otherFormat){
      case "PLUS":
        return "+";
      case "MINUS":
        return "-";
      case "TIMES":
        return "*";
      case "DIV":
        return "/";
      case "GT":
        return ">";
      case "LT":
        return "<";
      case "GE":
        return ">=";
      case "LE":
        return "<=";
      case "EQ":
        return "==";
      default:
        return "";
    }
  }

  @Override
  public String visit(UminusExpression uminusExpression, SymbolTable arg) {
    String expr = uminusExpression.getExpression().accept(this, arg);
    return String.format("-%s", expr);
  }

  @Override
  public String visit(DoubleConst doubleConst, SymbolTable arg) {
    return Double.toString(doubleConst.getValue());
  }

  @Override
  public String visit(IntegerConst integerConst, SymbolTable arg) {
    return Integer.toString(integerConst.getValue());
  }

  @Override
  public String visit(StringConst stringConst, SymbolTable arg) {
    return String.format("\"%s\"", stringConst.getValue());
  }

  @Override
  public String visit(NotExpression notExpression, SymbolTable arg) {
    String expr = notExpression.getExpression().accept(this, arg);
    return String.format("!%s", expr);
  }

  @Override
  public String visit(BooleanConst booleanConst, SymbolTable arg) {
    return Boolean.toString(booleanConst.getValue());
  }

  @Override
  public String visit(RelationalExpression relationalExpression, SymbolTable arg) {
    String left = relationalExpression.getLeftOperand().accept(this, arg);
    String right = relationalExpression.getRightOperand().accept(this, arg);
    String op = this.formatOp(relationalExpression.getRelOp());
    return String.format("%s %s %s", left, op, right);
  }

  @Override
  public String visit(AssignStatement assignStatement, SymbolTable arg) {
    String left = assignStatement.getLeft().accept(this, arg);
    String right = assignStatement.getRight().accept(this, arg);
    return String.format("%s = %s;", left, right);
  }
}
