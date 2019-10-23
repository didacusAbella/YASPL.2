package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.error.ErrorHandler;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.*;
import com.didacusabella.yaspl.type.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author didacus
 */
public class TypeCheckVisitor implements Visitor<Type, SymbolTable> {
  
  private final ErrorHandler handler;

  public TypeCheckVisitor(ErrorHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public Type visit(Program program, SymbolTable arg) {
    arg.enterScope();
    program.getDeclarations().forEach(this.typeCheck(arg));
    program.getStatements().forEach(this.typeCheck(arg));
    arg.exitScope();
    return PrimitiveType.NULL;
  }
  
  private Consumer<? super AstNode> typeCheck(SymbolTable arg){
    return (AstNode node) -> node.accept(this, arg);
  }

  @Override
  public Type visit(VariableDeclaration variableDeclaration, SymbolTable arg) {
    Type varType = variableDeclaration.getType().accept(this, arg);
    variableDeclaration.getVariables().forEach(this.typeCheck(arg));
    return varType;
  }

  @Override
  public Type visit(FunctionDeclaration functionDeclaration, SymbolTable arg) {
    Type fType = functionDeclaration.getName().accept(this, arg);
    arg.enterScope();
    functionDeclaration.getInputs().forEach(this.typeCheck(arg));
    functionDeclaration.getOutputs().forEach(this.typeCheck(arg));
    functionDeclaration.getBody().accept(this, arg);
    arg.exitScope();
    return fType;
  }

  @Override
  public Type visit(Variable variable, SymbolTable arg) {
    return arg.lookup(variable.getValue()).get().getType();
  }

  @Override
  public Type visit(TypeDenoter type, SymbolTable arg) {
    return type.typeFactory();
  }

  @Override
  public Type visit(Identifier identifier, SymbolTable arg) {
    Type type = arg.lookup(identifier.getValue()).get().getType();
    identifier.setType(type);
    return type;
  }

  @Override
  public Type visit(ParameterDeclaration parameterDeclaration, SymbolTable arg) {
    Type outType = parameterDeclaration.getType().accept(this, arg);
    parameterDeclaration.getVariables().forEach(this.typeCheck(arg));
    return outType;
  }

  @Override
  public Type visit(Body body, SymbolTable arg) {
    body.getVariableDeclarations().forEach(this.typeCheck(arg));
    body.getStatements().forEach(this.typeCheck(arg));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(ReadStatement readStatement, SymbolTable arg) {
    CompositeType varType = new CompositeType(new ArrayList<>());
    readStatement.getIdentifiers().forEach(i -> varType.addType(i.accept(this, arg)));
    CompositeType types = new CompositeType(new ArrayList<>());
    readStatement.getTypes().forEach(t -> types.addType(t.accept(this, arg)));
    if(!varType.equals(types))
      this.handler.reportTypeMismatch(types, varType, readStatement);
    return varType;
  }

  @Override
  public Type visit(WriteStatement writeStatement, SymbolTable arg) {
    writeStatement.getExpressions().forEach(this.typeCheck(arg));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(FunctionCall functionCall, SymbolTable arg) {
    CompositeType input = new CompositeType(new ArrayList<>());
    functionCall.getInputs().forEach(e -> input.addType(e.accept(this, arg)));
    Type output = functionCall.getName().accept(this, arg);
    if(!input.equals(output))
      this.handler.reportTypeMismatch(output, input, functionCall);
    return input;
  }

  @Override
  public Type visit(CompositeStatement compositeStatement, SymbolTable arg) {
    compositeStatement.getStatements().forEach(this.typeCheck(arg));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(WhileStatement whileStatement, SymbolTable arg) {
    Type condType = whileStatement.getCondition().accept(this, arg);
    if(!condType.equals(PrimitiveType.BOOL))
      this.handler.reportTypeMismatch(PrimitiveType.BOOL, condType, whileStatement);
    arg.enterScope();
    whileStatement.getBody().accept(this, arg);
    arg.exitScope();
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    Type condIf = ifThenStatement.getIfCondition().accept(this, arg);
    if(!condIf.equals(PrimitiveType.BOOL))
      this.handler.reportTypeMismatch(PrimitiveType.BOOL, condIf, ifThenStatement);
    ifThenStatement.getThenStatement().accept(this, arg);
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    Type condIf = ifThenElseStatement.getIfCondition().accept(this, arg);
    if(!condIf.equals(PrimitiveType.BOOL))
      this.handler.reportTypeMismatch(PrimitiveType.BOOL, condIf, ifThenElseStatement);
    ifThenElseStatement.getThenStatement().accept(this, arg);
    ifThenElseStatement.getElseStatement().accept(this, arg);
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(BinaryExpression binaryExpression, SymbolTable arg) {
    Type left = binaryExpression.getLeftOperand().accept(this, arg);
    Type right = binaryExpression.getRightOperand().accept(this, arg);
    Type result = PrimitiveType.NULL;
    switch(binaryExpression.getOp()){
      case "PLUS":
        result = left.checkAdd((PrimitiveType) right);
        break;
      case "MINUS":
        result = left.checkSub((PrimitiveType) right);
        break;
      case "TIMES":
        result = left.checkMul((PrimitiveType) right);
        break;
      case "DIV":
        result = left.checkDiv((PrimitiveType) right);
        break;
    }
    if(result.equals(PrimitiveType.NULL))
      this.handler.reportTypeMismatch(left, result, binaryExpression);
    binaryExpression.setType(result);
    return result;
  }

  @Override
  public Type visit(UminusExpression uminusExpression, SymbolTable arg) {
    Type type = uminusExpression.getExpression().accept(this, arg);
    if(!type.equals(PrimitiveType.INT) && !type.equals(PrimitiveType.DOUBLE))
      this.handler.reportTypeMismatch(PrimitiveType.DOUBLE, type, uminusExpression);
    return type;
  }

  @Override
  public Type visit(DoubleConst doubleConst, SymbolTable arg) {
    doubleConst.setType(PrimitiveType.DOUBLE);
    return doubleConst.getType();
  }

  @Override
  public Type visit(IntegerConst integerConst, SymbolTable arg) {
    integerConst.setType(PrimitiveType.INT);
    return integerConst.getType();
  }

  @Override
  public Type visit(StringConst stringConst, SymbolTable arg) {
    stringConst.setType(PrimitiveType.STRING);
    return stringConst.getType();
  }

  @Override
  public Type visit(NotExpression notExpression, SymbolTable arg) {
    Type type = notExpression.getExpression().accept(this, arg);
    if(!type.equals(PrimitiveType.BOOL))
      this.handler.reportTypeMismatch(PrimitiveType.BOOL, type, notExpression);
    return type;
  }

  @Override
  public Type visit(BooleanConst booleanConst, SymbolTable arg) {
    booleanConst.setType(PrimitiveType.BOOL);
    return booleanConst.getType();
  }

  @Override
  public Type visit(RelationalExpression relationalExpression, SymbolTable arg) {
    Type left = relationalExpression.getLeftOperand().accept(this, arg);
    Type right = relationalExpression.getRightOperand().accept(this, arg);
    Type result = left.checkRel((PrimitiveType) right);
    if(result.equals(PrimitiveType.NULL))
      this.handler.reportTypeMismatch(PrimitiveType.BOOL, result, relationalExpression);
    return result;
  }

  @Override
  public Type visit(AssignStatement assignStatement, SymbolTable arg) {
    Type left = assignStatement.getLeft().accept(this, arg);
    Type right = assignStatement.getRight().accept(this, arg);
    if(!left.equals(right))
      this.handler.reportTypeMismatch(left, right, assignStatement);
    return right;
  }
  
}
