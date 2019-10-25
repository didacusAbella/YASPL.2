package com.didacusabella.yaspl.visitor;


import com.didacusabella.yaspl.error.ErrorHandler;
import com.didacusabella.yaspl.semantic.SymbolKind;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.semantic.SymbolTableRecord;
import com.didacusabella.yaspl.syntax.*;
import com.didacusabella.yaspl.type.Type;
import java.util.List;

/**
 *
 * @author didacus
 */
public class ContextVisitor implements Visitor<Boolean, SymbolTable> {
  
  private final ErrorHandler handler;

  public ContextVisitor(ErrorHandler handler) {
    this.handler = handler;
  }

  @Override
  public Boolean visit(Program program, SymbolTable arg) {
    arg.enterScope();
    boolean areDeclsSafe = this.checkContext(program.getDeclarations(), arg);
    boolean areStmtsSafe = this.checkContext(program.getStatements(), arg);
    boolean isProgramSafe = areDeclsSafe && areStmtsSafe;
    if(!isProgramSafe)
      this.handler.reportError("Program Error", program);
    arg.exitScope();
    return isProgramSafe;
  }
  
  private boolean checkContext(List<? extends AstNode> nodes, SymbolTable arg) {
    if(nodes.isEmpty())
      return true;
    else
      return nodes.stream().allMatch(node -> node.accept(this, arg));
  }

  @Override
  public Boolean visit(VariableDeclaration variableDeclaration, SymbolTable arg) {
    boolean areVarsSafe = this.checkContext(variableDeclaration.getVariables(), arg);
    if(!areVarsSafe) {
      this.handler.reportError("Variable Declaration Error", variableDeclaration);
    }
    else {
      Type type = variableDeclaration.getType().typeFactory();
      variableDeclaration.getVariables().forEach(v -> arg.addEntry(v.getValue(), 
              new SymbolTableRecord(type, SymbolKind.VARIABLE)));
    }
    return areVarsSafe;
  }

  @Override
  public Boolean visit(FunctionDeclaration functionDeclaration, SymbolTable arg) {
    boolean isFunctionSafe = functionDeclaration.getName().accept(this, arg);
    if(!isFunctionSafe){
      this.handler.reportYetDefined(functionDeclaration);
    }else {
      arg.enterScope();
      boolean areInputsSafe = this.checkContext(functionDeclaration.getInputs(), arg);
      boolean areOutputsSafe = this.checkContext(functionDeclaration.getOutputs(), arg);
      boolean isBodySafe = functionDeclaration.getBody().accept(this, arg);
      isFunctionSafe = areInputsSafe && areOutputsSafe && isBodySafe;
      if(!isFunctionSafe)
        this.handler.reportError("Function Declaration error", functionDeclaration);
      arg.exitScope();
      String name = functionDeclaration.getName().getValue();
      arg.addEntry(name, new SymbolTableRecord(functionDeclaration.codomain(), SymbolKind.FUNCTION));
    }
    return isFunctionSafe;
  }

  @Override
  public Boolean visit(Variable variable, SymbolTable arg) {
    return arg.lookup(variable.getValue()).isEmpty();
  }

  @Override
  public Boolean visit(TypeDenoter type, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(Identifier identifier, SymbolTable arg) {
    return arg.lookup(identifier.getValue()).isPresent();
  }

  @Override
  public Boolean visit(ParameterDeclaration parameterDeclaration, SymbolTable arg) {
    boolean areVarsSafe = this.checkContext(parameterDeclaration.getVariables(), arg);
    if(!areVarsSafe){
      this.handler.reportError("Parameter Declaration error", parameterDeclaration);
    } else {
      Type type = parameterDeclaration.getType().typeFactory();
      parameterDeclaration.getVariables().forEach(v -> arg.addEntry(v.getValue(), 
              new SymbolTableRecord(type, SymbolKind.OUTPUT)));
    }
    return areVarsSafe;
  }

  @Override
  public Boolean visit(Body body, SymbolTable arg) {
    boolean areDeclsSafe = this.checkContext(body.getVariableDeclarations(), arg);
    boolean areStmtsSafe = this.checkContext(body.getStatements(), arg);
    boolean isBodySafe = areDeclsSafe && areStmtsSafe;
    if(!isBodySafe)
      this.handler.reportError("Body error", body);
    return isBodySafe;
  }

  @Override
  public Boolean visit(ReadStatement readStatement, SymbolTable arg) {
    boolean areVarsSafe = this.checkContext(readStatement.getIdentifiers(), arg);
    if(!areVarsSafe)
      this.handler.reportError("Read Statement Error", readStatement);
    return areVarsSafe;
  }

  @Override
  public Boolean visit(WriteStatement writeStatement, SymbolTable arg) {
    boolean areVarsSafe = this.checkContext(writeStatement.getExpressions(), arg);
    if(!areVarsSafe)
      this.handler.reportError("Write Statement Error", writeStatement);
    return areVarsSafe;
  }

  @Override
  public Boolean visit(FunctionCall functionCall, SymbolTable arg) {
    boolean isFunCallSafe = functionCall.getName().accept(this, arg);
    if(!isFunCallSafe){
      this.handler.reportNotDefined(functionCall);
    } else {
      boolean areInputsSafe = this.checkContext(functionCall.getInputs(), arg);
      boolean areOutputsSafe = this.checkContext(functionCall.getOutputs(), arg);
      isFunCallSafe = areInputsSafe && areOutputsSafe;
      if(!isFunCallSafe)
        this.handler.reportError("Function Call Error", functionCall);
    }
    return isFunCallSafe;
  }

  @Override
  public Boolean visit(CompositeStatement compositeStatement, SymbolTable arg) {
    boolean areStmtsSafe = this.checkContext(compositeStatement.getStatements(), arg);
    if(!areStmtsSafe)
      this.handler.reportError("Composite Statement Error", compositeStatement);
    return areStmtsSafe;
  }

  @Override
  public Boolean visit(WhileStatement whileStatement, SymbolTable arg) {
    boolean isConditionSafe = whileStatement.getCondition().accept(this, arg);
    arg.enterScope();
    boolean areStmtsSafe = whileStatement.getBody().accept(this, arg);
    boolean isWhileSafe = isConditionSafe && areStmtsSafe;
    if(!isWhileSafe)
      this.handler.reportError("While Statement Error", whileStatement);
    arg.exitScope();
    return isWhileSafe;
  }

  @Override
  public Boolean visit(IfThenStatement ifThenStatement, SymbolTable arg) {
    boolean isConditionSafe = ifThenStatement.getIfCondition().accept(this, arg);
    boolean isThenSafe = ifThenStatement.getThenStatement().accept(this, arg);
    boolean isIfSafe = isConditionSafe && isThenSafe;
    if(!isIfSafe)
      this.handler.reportError("If Error", ifThenStatement);
    return isIfSafe;
  }

  @Override
  public Boolean visit(IfThenElseStatement ifThenElseStatement, SymbolTable arg) {
    boolean isConditionSafe = ifThenElseStatement.getIfCondition().accept(this, arg);
    boolean isThenSafe = ifThenElseStatement.getThenStatement().accept(this, arg);
    boolean isElseSafe = ifThenElseStatement.getElseStatement().accept(this, arg);
    boolean isIfElseSafe = isConditionSafe && isThenSafe && isElseSafe;
    if(!isIfElseSafe)
      this.handler.reportError("IF Else Error", ifThenElseStatement);
    return isIfElseSafe;
  }

  @Override
  public Boolean visit(BinaryExpression binaryExpression, SymbolTable arg) {
    boolean isLeftSafe = binaryExpression.getLeftOperand().accept(this, arg);
    boolean isRightSafe = binaryExpression.getRightOperand().accept(this, arg);
    boolean isBinarySafe = isLeftSafe && isRightSafe;
    if(!isBinarySafe)
      this.handler.reportError("Binary Expression Error", binaryExpression);
    return isBinarySafe;
  }

  @Override
  public Boolean visit(UminusExpression uminusExpression, SymbolTable arg) {
    boolean isExprSafe = uminusExpression.getExpression().accept(this, arg);
    if(!isExprSafe)
      this.handler.reportError("UminusExpression Error", uminusExpression);
    return isExprSafe;
  }

  @Override
  public Boolean visit(DoubleConst doubleConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(IntegerConst integerConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(StringConst stringConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(NotExpression notExpression, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(BooleanConst booleanConst, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(RelationalExpression relationalExpression, SymbolTable arg) {
    return true;
  }

  @Override
  public Boolean visit(AssignStatement assignStatement, SymbolTable arg) {
    boolean isLeftSafe = assignStatement.getLeft().accept(this, arg);
    boolean isRightSafe = assignStatement.getRight().accept(this, arg);
    boolean isAssignSafe = isLeftSafe && isRightSafe;
    if(!isAssignSafe)
      this.handler.reportError("Assign Statement Error", assignStatement);
    return isAssignSafe;
  }
}