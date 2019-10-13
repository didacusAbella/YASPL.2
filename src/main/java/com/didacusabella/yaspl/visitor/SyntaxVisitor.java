package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;
import java.util.function.Consumer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Syntax visitor. It perform depth first visiting, creating an xml file as
 * output
 */
public class SyntaxVisitor implements Visitor<Element, Document> {

  @Override
  public Element visit(Program program, Document param) {
    Element el = param.createElement("ProgramOp");
    program.getDeclarations().forEach(this.append(el, param));
    program.getStatements().forEach(this.append(el, param));
    param.appendChild(el);
    return el;
  }

  private Consumer<? super AstNode> append(Element parent, Document doc) {
    return (AstNode node) -> parent.appendChild(node.accept(this, doc));
  }

  @Override
  public Element visit(VariableDeclaration variableDeclaration, Document param) {
    Element el = param.createElement("VarDeclOp");
    el.appendChild(variableDeclaration.getType().accept(this, param));
    variableDeclaration.getVariables().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(FunctionDeclaration functionDeclaration, Document param) {
    Element el = param.createElement("DefDeclOp");
    el.appendChild(functionDeclaration.getFunctionName().accept(this, param));
    functionDeclaration.getVariableDeclarations().forEach(this.append(el, param));
    functionDeclaration.getParameterDeclarations().forEach(this.append(el, param));
    el.appendChild(functionDeclaration.getBody().accept(this, param));
    return el;
  }

  @Override
  public Element visit(Variable variable, Document param) {
    Element el = param.createElement("Variable");
    el.setAttribute("lexeme", variable.getName());
    return el;
  }

  @Override
  public Element visit(TypeDenoter type, Document param) {
    Element el = param.createElement("Type");
    el.setAttribute("kind", type.getKind());
    return el;
  }

  @Override
  public Element visit(Identifier identifier, Document param) {
    Element el = param.createElement("Identifier");
    el.setAttribute("lexeme", identifier.getName());
    return el;
  }

  @Override
  public Element visit(ParameterDeclaration parameterDeclaration, Document param) {
    Element el = param.createElement("ParDeclOp");
    el.appendChild(parameterDeclaration.getType().accept(this, param));
    parameterDeclaration.getVariables().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(Body body, Document param) {
    Element el = param.createElement("BodyOp");
    body.getVariableDeclarations().forEach(this.append(el, param));
    body.getStatements().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(ReadStatement readStatement, Document param) {
    Element el = param.createElement("ReadOp");
    readStatement.getIdentifiers().forEach(this.append(el, param));
    readStatement.getTypes().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(WriteStatement writeStatement, Document param) {
    Element el = param.createElement("WriteOp");
    writeStatement.getExpressions().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(FunctionCall functionCall, Document param) {
    Element el = param.createElement("DefCallOp");
    el.appendChild(functionCall.getFunctionName().accept(this, param));
    functionCall.getExpressions().forEach(this.append(el, param));
    functionCall.getVariables().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(CompositeStatement compositeStatement, Document param) {
    Element el = param.createElement("CompOp");
    compositeStatement.getStatements().forEach(this.append(el, param));
    return el;
  }

  @Override
  public Element visit(WhileStatement whileStatement, Document param) {
    Element el = param.createElement("WhileOp");
    el.appendChild(whileStatement.getWhileCondition().accept(this, param));
    el.appendChild(whileStatement.getWhileStatement().accept(this, param));
    return el;
  }

  @Override
  public Element visit(IfThenStatement ifThenStatement, Document param) {
    Element el = param.createElement("IfThenOp");
    el.appendChild(ifThenStatement.getIfCondition().accept(this, param));
    el.appendChild(ifThenStatement.getThenStatement().accept(this, param));
    return el;
  }

  @Override
  public Element visit(IfThenElseStatement ifThenElseStatement, Document param) {
    Element el = param.createElement("IfThenElseOp");
    el.appendChild(ifThenElseStatement.getIfCondition().accept(this, param));
    el.appendChild(ifThenElseStatement.getThenStatement().accept(this, param));
    el.appendChild(ifThenElseStatement.getElseStatement().accept(this, param));
    return el;
  }

  @Override
  public Element visit(BinaryExpression binaryExpression, Document param) {
    Element el = param.createElement("BinaryOp");
    el.appendChild(binaryExpression.getLeftOperand().accept(this, param));
    el.appendChild(binaryExpression.getRightOperand().accept(this, param));
    el.setAttribute("operation", binaryExpression.getOp());
    return el;
  }

  @Override
  public Element visit(UminusExpression uminusExpression, Document param) {
    Element el = param.createElement("UminusOp");
    el.appendChild(uminusExpression.getExpression().accept(this, param));
    return el;
  }

  @Override
  public Element visit(DoubleConst doubleConst, Document param) {
    Element el = param.createElement("DoubleConst");
    el.setAttribute("value", String.valueOf(doubleConst.getDoubleValue()));
    return el;
  }

  @Override
  public Element visit(IntegerConst integerConst, Document param) {
    Element el = param.createElement("IntegerConst");
    el.setAttribute("value", String.valueOf(integerConst.getIntValue()));
    return el;
  }

  @Override
  public Element visit(StringConst stringConst, Document param) {
    Element el = param.createElement("StringConst");
    el.setAttribute("value", stringConst.getStringValue());
    return el;
  }

  @Override
  public Element visit(NotExpression notExpression, Document param) {
    Element el = param.createElement("NotOp");
    el.appendChild(notExpression.getExpression().accept(this, param));
    return el;
  }

  @Override
  public Element visit(TrueExpression trueExpression, Document param) {
    Element el = param.createElement("TrueExpr");
    el.setAttribute("value", String.valueOf(trueExpression.getValue()));
    return el;
  }

  @Override
  public Element visit(FalseExpression falseExpression, Document param) {
    Element el = param.createElement("FalseExpr");
    el.setAttribute("value", String.valueOf(falseExpression.getValue()));
    return el;
  }

  @Override
  public Element visit(RelationalExpression relationalExpression, Document param) {
    Element el = param.createElement("RelOp");
    el.setAttribute("operation", relationalExpression.getRelOp());
    el.appendChild(relationalExpression.getLeftOperand().accept(this, param));
    el.appendChild(relationalExpression.getRightOperand().accept(this, param));
    return el;
  }

  @Override
  public Element visit(AssignStatement assignStatement, Document param) {
    Element el = param.createElement("AssignOp");
    el.appendChild(assignStatement.getIdentifier().accept(this, param));
    el.appendChild(assignStatement.getExpression().accept(this, param));
    return el;
  }

}
