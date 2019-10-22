package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.semantic.SymbolKind;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.*;
import com.didacusabella.yaspl.type.PrimitiveType;
import com.didacusabella.yaspl.type.Type;
import java.util.List;
import java.util.StringJoiner;

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
    String decl = this.stringify(program.getDeclarations(), arg);
    String sts = this.stringify(program.getStatements(), arg);
    return root.replace("$declarations$", decl).replace("$statements$", sts);
  }
  
  private String stringify(List<? extends AstNode> nodes, SymbolTable table){
    StringBuilder builder = new StringBuilder();
    nodes.forEach(node -> builder.append(node.accept(this, table)));
    return builder.toString();
  }

  @Override
  public String visit(VariableDeclaration variableDeclaration, SymbolTable arg) {
    String type = variableDeclaration.getType().accept(this, arg);
    StringJoiner inputJoiner = new StringJoiner(",");
    variableDeclaration.getVariables().forEach(v -> inputJoiner.add(v.accept(this, arg)));
    return String.format("%s %s;", type, inputJoiner.toString());
  }

  @Override
  public String visit(FunctionDeclaration functionDeclaration, SymbolTable arg) {
    StringBuilder functionBuilder = new StringBuilder();
    functionBuilder.append("void");
    String fName = functionDeclaration.getName().accept(this, arg);
    functionBuilder.append(fName);
    functionBuilder.append('(');
    StringJoiner inputs = new StringJoiner(",");
    functionDeclaration.getInputs().forEach(input -> inputs.add(input.accept(this, arg)));
    functionDeclaration.getOutputs().forEach(output -> inputs.add(output.accept(this, arg)));
    functionBuilder.append('{');
    functionBuilder.append('\n');
    functionBuilder.append(functionDeclaration.getBody().accept(this, arg));
    functionBuilder.append('\n');
    functionBuilder.append('}');
    return functionBuilder.toString();
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
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    StringJoiner inputJoiner = new StringJoiner(",");
    parameterDeclaration.getVariables().forEach(v -> inputJoiner.add(v.accept(this, arg)));
    return String.format("%s %s", type, inputJoiner.toString());
  }

  @Override
  public String visit(Body body, SymbolTable arg) {
    StringJoiner bodyJoiner = new StringJoiner("\n");
    body.getVariableDeclarations().forEach(vd -> bodyJoiner.add(vd.accept(this, arg)));
    body.getStatements().forEach(st -> bodyJoiner.add(st.accept(this, arg)));
    return bodyJoiner.toString();
  }

  @Override
  public String visit(ReadStatement readStatement, SymbolTable arg) {
    StringJoiner typeJoiner = new StringJoiner(" ");
    readStatement.getTypes().forEach(t -> typeJoiner.add(this.formatType(t.typeFactory())));
    StringJoiner read = new StringJoiner(",");
    readStatement.getIdentifiers().forEach(i -> read.add(i.accept(this, arg)));
    return String.format("scanf(%s, %s);", typeJoiner.toString(), read.toString());
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
    StringJoiner typeJoiner = new StringJoiner(",");
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
    functionCall.getOutputs().forEach(o -> funJoiner.add(o.accept(this, arg)));
    String fName = functionCall.getName().accept(this, arg);
    return String.format("%s(%s);", fName, funJoiner.toString());
  }

  @Override
  public String visit(CompositeStatement compositeStatement, SymbolTable arg) {
    StringJoiner compJoiner = new StringJoiner("\n");
    compositeStatement.getStatements().forEach(s -> compJoiner.add(s.accept(this, arg)));
    return compJoiner.toString();
  }

  @Override
  public String visit(WhileStatement whileStatement, SymbolTable arg) {
    String condition = whileStatement.getCondition().accept(this, arg);
    String body = whileStatement.getBody().accept(this, arg);
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
    return String.format("%s %s %s;", left, right, op);
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
