package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.semantic.SymbolKind;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.semantic.SymbolTableRecord;
import com.didacusabella.yaspl.syntax.*;
import com.didacusabella.yaspl.type.PrimitiveType;
import java.util.function.Consumer;
import com.didacusabella.yaspl.type.Type;
import java.util.List;
import java.util.Optional;


/**
 * Semantic visitor for YASPL language
 */
public class SemanticVisitor implements Visitor<Type, SymbolTable>  {
  
  private final ErrorHandler handler;

  public SemanticVisitor(ErrorHandler handler) {
    this.handler = handler;
  }

  @Override
  public Type visit(Program program, SymbolTable param) {
    param.enterScope();
    program.getDeclarations().forEach(this.check(param));
    program.getDeclarations().forEach(this.check(param));
    param.exitScope();
    return PrimitiveType.NULL;
  }
  
  private Consumer<? super AstNode> check(SymbolTable table){
    return (AstNode node) -> node.accept(this, table);
  }

  @Override
  public Type visit(VariableDeclaration variableDeclaration, SymbolTable param) {
    Type varType =  variableDeclaration.getType().accept(this, param);
    variableDeclaration.getVariables().forEach(this.check(param));
    variableDeclaration.getVariables().forEach(v -> param.addEntry(v.getName(), new SymbolTableRecord(varType, SymbolKind.VARIABLE)));
    return varType;
  }

  @Override
  public Type visit(FunctionDeclaration functionDeclaration, SymbolTable param) {
    functionDeclaration.getFunctionName().accept(this, param);
    String functionName = functionDeclaration.getFunctionName().getName();
    SymbolTableRecord str = new SymbolTableRecord(null, SymbolKind.FUNCTION);
    param.addEntry(functionName, str);
    param.enterScope();
    functionDeclaration.getVariableDeclarations().forEach(this.check(param));
    functionDeclaration.getParameterDeclarations().forEach(this.check(param));
    functionDeclaration.getBody().accept(this, param);
    param.exitScope();
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(Variable variable, SymbolTable param) {
    Optional<SymbolTableRecord> record = param.lookup(variable.getName());
    if(record.isEmpty()){
      return PrimitiveType.NULL;
    } else {
      this.handler.reportError("Variable aleady declared", variable);
      return record.get().getType();
    }
  }

  @Override
  public Type visit(TypeDenoter type, SymbolTable param) {
    return type.typeFactory();
  }

  @Override
  public Type visit(Identifier identifier, SymbolTable param) {
    Optional<SymbolTableRecord> record = param.lookup(identifier.getName());
    if(record.isPresent()) {
      return record.get().getType();
    } else {
      this.handler.reportError("Variable not exist", identifier);
      return PrimitiveType.NULL;
    }
  }

  @Override
  public Type visit(ParameterDeclaration parameterDeclaration, SymbolTable param) {
    Type type = parameterDeclaration.getType().accept(this, param);
    parameterDeclaration.getVariables().forEach(this.check(param));
    parameterDeclaration.getVariables().forEach(v -> param.addEntry(v.getName(), new SymbolTableRecord(type, SymbolKind.OUTPUT)));
    return type;
  }

  @Override
  public Type visit(Body body, SymbolTable param) {
    body.getVariableDeclarations().forEach(this.check(param));
    body.getStatements().forEach(this.check(param));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(ReadStatement readStatement, SymbolTable param) {
    readStatement.getIdentifiers().forEach(this.check(param));
    readStatement.getTypes().forEach(this.check(param));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(WriteStatement writeStatement, SymbolTable param) {
    writeStatement.getExpressions().forEach(this.check(param));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(FunctionCall functionCall, SymbolTable param) {
    Type record = functionCall.getFunctionName().accept(this, param);
    functionCall.getExpressions().forEach(this.check(param));
    functionCall.getVariables().forEach(this.check(param));
    return record;
  }

  @Override
  public Type visit(CompositeStatement compositeStatement, SymbolTable param) {
    compositeStatement.getStatements().forEach(this.check(param));
    return PrimitiveType.NULL;
  }

  @Override
  public Type visit(WhileStatement whileStatement, SymbolTable param) {
    Type whileCond = whileStatement.getWhileCondition().accept(this, param);
     whileStatement.getWhileStatement().accept(this, param);
    if(whileCond != PrimitiveType.BOOL)
      this.handler.reportError("While Condition must be true", whileStatement);
    return whileCond;
  }

  @Override
  public Type visit(IfThenStatement ifThenStatement, SymbolTable param) {
    Type ifCond = ifThenStatement.getIfCondition().accept(this, param);
    ifThenStatement.getThenStatement().accept(this, param);
    if(ifCond != PrimitiveType.BOOL)
      this.handler.reportError("If Condition must be Bool", ifThenStatement);
    return ifCond;
  }

  @Override
  public Type visit(IfThenElseStatement ifThenElseStatement, SymbolTable param) {
    Type ifElseCond = ifThenElseStatement.getIfCondition().accept(this, param);
    ifThenElseStatement.getThenStatement().accept(this, param);
    ifThenElseStatement.getElseStatement().accept(this, param);
    if(ifElseCond != PrimitiveType.BOOL)
      this.handler.reportError("If Condition must be Bool", ifThenElseStatement);
    return ifElseCond;
  }

  @Override
  public Type visit(BinaryExpression binaryExpression, SymbolTable param) {
    Type leftType = binaryExpression.getLeftOperand().accept(this, param);
    Type rightType = binaryExpression.getRightOperand().accept(this, param);
    Type result;
    switch(binaryExpression.getOp()){
      case "PLUS":
        result = leftType.checkAdd((PrimitiveType) rightType);
        break;
      case "MINUS":
        result = leftType.checkSub((PrimitiveType) rightType);
        break;
      case "TIMES":
        result = leftType.checkMul((PrimitiveType) rightType);
        break;
      case "DIV":
        result = leftType.checkDiv((PrimitiveType) rightType);
        break;
      default:
        this.handler.reportError("Invalid Operator", binaryExpression);
        result = PrimitiveType.NULL;
    }
    binaryExpression.setType(result);
    return result;
  }

  @Override
  public Type visit(UminusExpression uminusExpression, SymbolTable param) {
    PrimitiveType type = (PrimitiveType) uminusExpression.getExpression().accept(this, param);
    if(this.isNotNumber(type))
      this.handler.reportError("Minus accept only numbers", uminusExpression);
    uminusExpression.setType(type);
    return type;
  }
  
  private boolean isNotNumber(PrimitiveType type) {
    return type != PrimitiveType.DOUBLE || type != PrimitiveType.INT;
  }

  @Override
  public Type visit(DoubleConst doubleConst, SymbolTable param) {
    return PrimitiveType.DOUBLE;
  }

  @Override
  public Type visit(IntegerConst integerConst, SymbolTable param) {
    return PrimitiveType.INT;
  }

  @Override
  public Type visit(StringConst stringConst, SymbolTable param) {
    return PrimitiveType.STRING;
  }

  @Override
  public Type visit(NotExpression notExpression, SymbolTable param) {
    PrimitiveType type = (PrimitiveType) notExpression.getExpression().accept(this, param);
    if(type != PrimitiveType.BOOL)
      this.handler.reportError("Not musy be bool", notExpression);
    notExpression.setType(type);
    return type;
  }

  @Override
  public Type visit(TrueExpression trueExpression, SymbolTable param) {
    return PrimitiveType.BOOL;
  }

  @Override
  public Type visit(FalseExpression falseExpression, SymbolTable param) {
    return PrimitiveType.BOOL;
  }

  @Override
  public Type visit(RelationalExpression relationalExpression, SymbolTable param) {
    Type leftOp = relationalExpression.getLeftOperand().accept(this, param);
    Type rightOp = relationalExpression.getRightOperand().accept(this, param);
    Type result = leftOp.checkRel((PrimitiveType) rightOp);
    if(result == PrimitiveType.NULL)
      this.handler.reportError("Relational expression must be Bool", relationalExpression);
    relationalExpression.setType(result);
    return result;
  }

  @Override
  public Type visit(AssignStatement assignStatement, SymbolTable param) {
    Type left = assignStatement.getIdentifier().accept(this, param);
    Type right = assignStatement.getExpression().accept(this, param);
    if(left != right)
      this.handler.reportError(String.format("Expected %s but found %s", left, right), assignStatement);
    return left;
  }
}

