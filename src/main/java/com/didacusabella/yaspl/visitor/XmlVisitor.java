package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;
import java.util.function.Consumer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author didacus
 */
public class XmlVisitor implements Visitor<Element, Document> {

  @Override
  public Element visit(Program program, Document arg) {
    Element element = arg.createElement("Program");
    program.getDeclarations().forEach(this.addParent(element, arg));
    program.getStatements().forEach(this.addParent(element, arg));
    arg.appendChild(element);
    return element;
  }
  
  private Consumer<? super AstNode> addParent(Element parent, Document arg){
    return (AstNode node) -> parent.appendChild(node.accept(this, arg));
  }

  @Override
  public Element visit(VariableDeclaration variableDeclaration, Document arg) {
    Element element = arg.createElement("VariableDeclaration");
    element.appendChild(variableDeclaration.getType().accept(this, arg));
    variableDeclaration.getVariables().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(FunctionDeclaration functionDeclaration, Document arg) {
    Element element = arg.createElement("FunctionDeclaration");
    element.appendChild(functionDeclaration.getName().accept(this, arg));
    functionDeclaration.getInputs().forEach(this.addParent(element, arg));
    functionDeclaration.getOutputs().forEach(this.addParent(element, arg));
    element.appendChild(functionDeclaration.getBody().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(Variable variable, Document arg) {
    Element element = arg.createElement("Variable");
    element.setAttribute("lexeme", variable.getValue());
    return element;
  }

  @Override
  public Element visit(TypeDenoter type, Document arg) {
    Element element = arg.createElement("TypeDenoter");
    element.setAttribute("kind", type.getValue());
    return element;
  }

  @Override
  public Element visit(Identifier identifier, Document arg) {
    Element element = arg.createElement("Identifier");
    element.setAttribute("lexeme", identifier.getValue());
    return element;
  }

  @Override
  public Element visit(ParameterDeclaration parameterDeclaration, Document arg) {
    Element element = arg.createElement("ParameterDeclaration");
    element.appendChild(parameterDeclaration.getType().accept(this, arg));
    parameterDeclaration.getVariables().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(Body body, Document arg) {
    Element element = arg.createElement("Body");
    body.getVariableDeclarations().forEach(this.addParent(element, arg));
    body.getStatements().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(ReadStatement readStatement, Document arg) {
    Element element = arg.createElement("ReadStatement");
    readStatement.getIdentifiers().forEach(this.addParent(element, arg));
    readStatement.getTypes().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(WriteStatement writeStatement, Document arg) {
    Element element = arg.createElement("WriteStatement");
    writeStatement.getExpressions().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(FunctionCall functionCall, Document arg) {
    Element element = arg.createElement("FunctionCall");
    element.appendChild(functionCall.getName().accept(this, arg));
    functionCall.getInputs().forEach(this.addParent(element, arg));
    functionCall.getOutputs().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(CompositeStatement compositeStatement, Document arg) {
    Element element = arg.createElement("CompositeStatement");
    compositeStatement.getStatements().forEach(this.addParent(element, arg));
    return element;
  }

  @Override
  public Element visit(WhileStatement whileStatement, Document arg) {
    Element element = arg.createElement("WhileStatement");
    element.appendChild(whileStatement.getCondition().accept(this, arg));
    element.appendChild(whileStatement.getBody().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(IfThenStatement ifThenStatement, Document arg) {
    Element element = arg.createElement("IfThenStatement");
    element.appendChild(ifThenStatement.getIfCondition().accept(this, arg));
    element.appendChild(ifThenStatement.getThenStatement().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(IfThenElseStatement ifThenElseStatement, Document arg) {
    Element element = arg.createElement("IfThenElseStatement");
    element.appendChild(ifThenElseStatement.getIfCondition().accept(this, arg));
    element.appendChild(ifThenElseStatement.getThenStatement().accept(this, arg));
    element.appendChild(ifThenElseStatement.getElseStatement().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(BinaryExpression binaryExpression, Document arg) {
    Element element = arg.createElement("BinaryExpression");
    element.setAttribute("operation", binaryExpression.getOp());
    element.appendChild(binaryExpression.getLeftOperand().accept(this, arg));
    element.appendChild(binaryExpression.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(UminusExpression uminusExpression, Document arg) {
    Element element = arg.createElement("UminusExpression");
    element.appendChild(uminusExpression.getExpression().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(DoubleConst doubleConst, Document arg) {
    Element element = arg.createElement("DoubleConst");
    element.setAttribute("value", Double.toString(doubleConst.getValue()));
    return element;
  }

  @Override
  public Element visit(IntegerConst integerConst, Document arg) {
    Element element = arg.createElement("IntConst");
    element.setAttribute("value", Integer.toString(integerConst.getValue()));
    return element;
  }

  @Override
  public Element visit(StringConst stringConst, Document arg) {
    Element element = arg.createElement("DoubleConst");
    element.setAttribute("value", stringConst.getValue());
    return element;
  }

  @Override
  public Element visit(NotExpression notExpression, Document arg) {
    Element element = arg.createElement("NotExpression");
    element.appendChild(notExpression.getExpression().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(BooleanConst booleanConst, Document arg) {
    Element element = arg.createElement("BooleanConst");
    element.setAttribute("value", Boolean.toString(booleanConst.getValue()));
    return element;
  }

  @Override
  public Element visit(RelationalExpression relationalExpression, Document arg) {
    Element element = arg.createElement("RelationalExpression");
    element.setAttribute("operand", relationalExpression.getRelOp());
    element.appendChild(relationalExpression.getLeftOperand().accept(this, arg));
    element.appendChild(relationalExpression.getRightOperand().accept(this, arg));
    return element;
  }

  @Override
  public Element visit(AssignStatement assignStatement, Document arg) {
    Element element = arg.createElement("AssignStatement");
    element.appendChild(assignStatement.getLeft().accept(this, arg));
    element.appendChild(assignStatement.getRight().accept(this, arg));
    return element;
  }
}
