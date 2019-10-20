package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;


public interface Visitor<T, P> {

  /**
   * Visit for the program node
   *
   * @param program the program node
   * @param param additional parameter
   */
   T visit(Program program, P arg);

  /**
   * Visit for the VariableDeclaration node
   *
   * @param variableDeclaration the node
   * @param param additional parameter
   */
   T visit(VariableDeclaration variableDeclaration, P arg);

  /**
   * Visit for the FunctionDeclaration node
   *
   * @param functionDeclaration the function node
   * @param param additional parameter
   */
   T visit(FunctionDeclaration functionDeclaration, P arg);

  /**
   * Visit for the Variable node
   *
   * @param variable the variable node
   * @param param additional parameter
   */
   T visit(Variable variable, P arg);

  /**
   * Visit for Type node
   *
   * @param type the type node
   * @param param additional parameter
   */
   T visit(TypeDenoter type, P arg);

  /**
   * Visit for Identifier node
   *
   * @param identifier the identifier node
   * @param param additional parameter
   */
   T visit(Identifier identifier, P arg);

  /**
   * Visit for ParameterDeclaration node
   *
   * @param parameterDeclaration the parameter node
   * @param param additional parameter
   */
   T visit(ParameterDeclaration parameterDeclaration, P arg);

  /**
   * Visit for Body node
   *
   * @param body the body node
   * @param param additional parameter
   */
   T visit(Body body, P arg);

  /**
   * Visit for ReadStatement node
   *
   * @param readStatement the read node
   * @param param additional parameter
   */
   T visit(ReadStatement readStatement, P arg);

  /**
   * Visit for WriteStatement node
   *
   * @param writeStatement the write node
   * @param param additional parameter
   */
   T visit(WriteStatement writeStatement, P arg);

  /**
   * Visit for FunctionCall
   *
   * @param functionCall the function call node
   * @param param additional parameter
   */
   T visit(FunctionCall functionCall, P arg);

  /**
   * Visit for CompositeStatement node
   *
   * @param compositeStatement the composite node
   * @param param additional parameter
   */
   T visit(CompositeStatement compositeStatement, P arg);

  /**
   * Visit for WhileStatement node
   *
   * @param whileStatement the while node
   * @param param additional parameter
   */
   T visit(WhileStatement whileStatement, P arg);

  /**
   * Visit for IfThenStatement node
   *
   * @param ifThenStatement the if node
   * @param param additional parameter
   */
   T visit(IfThenStatement ifThenStatement, P arg);

  /**
   * Visit for IfThenElseStatement node
   *
   * @param ifThenElseStatement the if else node
   * @param param additional parameter
   */
   T visit(IfThenElseStatement ifThenElseStatement, P arg);

  /**
   * Visit for BinaryExpression node
   *
   * @param binaryExpression the binary node
   * @param param additional parameter
   */
   T visit(BinaryExpression binaryExpression, P arg);

  /**
   * Visit for UminusExpression
   *
   * @param uminusExpression the uminus node
   * @param param additional parameter
   */
   T visit(UminusExpression uminusExpression, P arg);

  /**
   * Visit for DoubleConst node
   *
   * @param doubleConst the double node
   * @param param additional parameter
   */
   T visit(DoubleConst doubleConst, P arg);

  /**
   * Visit for IntegerConst node
   *
   * @param integerConst the int node
   * @param param additional parameter
   */
   T visit(IntegerConst integerConst, P arg);

  /**
   * Visit for the StringConst node
   *
   * @param stringConst the string node
   * @param param additional parameter
   */
   T visit(StringConst stringConst, P arg);

  /**
   * Visit for NotExpression node
   *
   * @param notExpression the not node
   * @param param additional parameter
   */
   T visit(NotExpression notExpression, P arg);

  /**
   * Visit for BooleanConst node
   *
   * @param booleanConst the false node
   * @param param additional parameter
   */
   T visit(BooleanConst booleanConst, P arg);

  /**
   * Visit for RelationalExpression node
   *
   * @param relationalExpression the relational node
   * @param param additional parameter
   */
   T visit(RelationalExpression relationalExpression, P arg);

  /**
   * Visit for AssingStatement node
   *
   * @param assignStatement the assign node
   * @param param additional parameter
   */
   T visit(AssignStatement assignStatement, P arg);

}
