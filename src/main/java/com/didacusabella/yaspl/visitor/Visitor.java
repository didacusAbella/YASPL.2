package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;

/**
 * Abstract visitor.T type is used for return type of the visitor.P type used
 for pass additional parameter. Use Void for pass nothing or return nothing
 * @param <T> return type
 * @param <P> additional parameter to pass
 */
public interface Visitor<T, P> {

  /**
   * Visit for the program node
   *
   * @param program the program node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(Program program, P param);

  /**
   * Visit for the VariableDeclaration node
   *
   * @param variableDeclaration the node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(VariableDeclaration variableDeclaration, P param);

  /**
   * Visit for the FunctionDeclaration node
   *
   * @param functionDeclaration the function node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(FunctionDeclaration functionDeclaration, P param);

  /**
   * Visit for the Variable node
   *
   * @param variable the variable node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(Variable variable, P param);

  /**
   * Visit for Type node
   *
   * @param type the type node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(TypeDenoter type, P param);

  /**
   * Visit for Identifier node
   *
   * @param identifier the identifier node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(Identifier identifier, P param);

  /**
   * Visit for ParameterDeclaration node
   *
   * @param parameterDeclaration the parameter node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(ParameterDeclaration parameterDeclaration, P param);

  /**
   * Visit for Body node
   *
   * @param body the body node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(Body body, P param);

  /**
   * Visit for ReadStatement node
   *
   * @param readStatement the read node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(ReadStatement readStatement, P param);

  /**
   * Visit for WriteStatement node
   *
   * @param writeStatement the write node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(WriteStatement writeStatement, P param);

  /**
   * Visit for FunctionCall
   *
   * @param functionCall the function call node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(FunctionCall functionCall, P param);

  /**
   * Visit for CompositeStatement node
   *
   * @param compositeStatement the composite node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(CompositeStatement compositeStatement, P param);

  /**
   * Visit for WhileStatement node
   *
   * @param whileStatement the while node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(WhileStatement whileStatement, P param);

  /**
   * Visit for IfThenStatement node
   *
   * @param ifThenStatement the if node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(IfThenStatement ifThenStatement, P param);

  /**
   * Visit for IfThenElseStatement node
   *
   * @param ifThenElseStatement the if else node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(IfThenElseStatement ifThenElseStatement, P param);

  /**
   * Visit for BinaryExpression node
   *
   * @param binaryExpression the binary node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(BinaryExpression binaryExpression, P param);

  /**
   * Visit for UminusExpression
   *
   * @param uminusExpression the uminus node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(UminusExpression uminusExpression, P param);

  /**
   * Visit for DoubleConst node
   *
   * @param doubleConst the double node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(DoubleConst doubleConst, P param);

  /**
   * Visit for IntegerConst node
   *
   * @param integerConst the int node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(IntegerConst integerConst, P param);

  /**
   * Visit for the StringConst node
   *
   * @param stringConst the string node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(StringConst stringConst, P param);

  /**
   * Visit for NotExpression node
   *
   * @param notExpression the not node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(NotExpression notExpression, P param);

  /**
   * Visit for TrueExpression node
   *
   * @param trueExpression the true node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(TrueExpression trueExpression, P param);

  /**
   * Visit for FalseExpression node
   *
   * @param falseExpression the false node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(FalseExpression falseExpression, P param);

  /**
   * Visit for RelationalExpression node
   *
   * @param relationalExpression the relational node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(RelationalExpression relationalExpression, P param);

  /**
   * Visit for AssingStatement node
   *
   * @param assignStatement the assign node
   * @param param additional parameter
   * @return a custom type
   */
  T visit(AssignStatement assignStatement, P param);

}
