package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;

/**
 * Abstract visitor
 */
public interface Visitor<T, P> {
    T visit(Program programNode, P additionalParameter);
    T visit(VariableDeclaration variableDeclarationNode, P additionalParameter);
    T visit(FunctionDeclaration functionDeclarationNode, P additionalParameter);
    T visit(Variable variableNode, P additionalParameter);
    T visit(BooleanType booleanTypeNode, P additionalParameter);
    T visit(IntegerType integerTypeNode, P additionalParameter);
    T visit(StringType stringTypeNode, P additionalParameter);
    T visit(DoubleType doubleTypeNode, P additionalParameter);
    T visit(Identifier identifierNode, P additionalParameter);
    T visit(ParameterDeclaration parameterDeclarationNode, P additionalParameter);
    T visit(Body bodyNode, P additionalParameter);
    T visit(ReadStatement readStatementNode, P additionalParameter);
    T visit(WriteStatement writeStatementNode, P additionalParameter);
    T visit(FunctionCall functionCallNode, P additionalParameter);
    T visit(CompositeStatement compositeStatementNode, P additionalParameter);
    T visit(WhileStatement whileStatementNode, P additionalParameter);
    T visit(IfThenStatement ifThenStatementNode, P additionalParameter);
    T visit(IfThenElseStatement ifThenElseStatementNode, P additionalParameter);
    T visit(SumExpression sumExpressionNode, P additionalParameter);
    T visit(DifferenceExpression differenceExpressionNode, P additionalParameter);
    T visit(ProductExpression productExpressionNode, P additionalParameter);
    T visit(DivideExpression divideExpressionNode, P additionalParameter);
    T visit(UminusExpression uminusExpressionNode, P additionalParameter);
    T visit(DoubleConst doubleConstNode, P additionalParameter);
    T visit(IntegerConst integerConstNode, P additionalParameter);
    T visit(StringConst stringConstNode, P additionalParameter);
    T visit(AndExpression andExpressionNode, P additionalParameter);
    T visit(OrExpression orExpressionNode, P additionalParameter);
    T visit(NotExpression notExpressionNode, P additionalParameter);
    T visit(TrueExpression trueExpressionNode, P additionalParameter);
    T visit(FalseExpression falseExpressionNode, P additionalParameter);
    T visit(GreatThanExpression greatThanExpressionNode, P additionalParameter);
    T visit(GreatThanEqualExpression greatThanEqualExpression, P additionalParameter);
    T visit(LessThanExpression lessThanExpression, P additionalParameter);
    T visit(LessThanEqualExpression lessThanEqualExpressionNode, P additionalParameter);
    T visit(EqualsExpression equalsExpressionNode, P additionalParameter);
    T visit(AssignStatement assignStatementNode, P additionalParameter);
}