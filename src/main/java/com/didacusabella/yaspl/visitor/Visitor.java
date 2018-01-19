package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;

/**
 * Abstract visitor
 */
public interface Visitor<T, P> {
    
    T visit(Program programNode, P param);
    T visit(VariableDeclaration variableDeclarationNode, P param);
    T visit(FunctionDeclaration functionDeclarationNode, P param);
    T visit(Variable variableNode, P param);
    T visit(Type typeNode, P param);
    T visit(Identifier identifierNode, P param);
    T visit(ParameterDeclaration parameterDeclarationNode, P param);
    T visit(Body bodyNode, P param);
    T visit(ReadStatement readStatementNode, P param);
    T visit(WriteStatement writeStatementNode, P param);
    T visit(FunctionCall functionCallNode, P param);
    T visit(CompositeStatement compositeStatementNode, P param);
    T visit(WhileStatement whileStatementNode, P param);
    T visit(IfThenStatement ifThenStatementNode, P param);
    T visit(IfThenElseStatement ifThenElseStatementNode, P param);
    T visit(BinaryExpression binaryExpressionNode, P param);
    T visit(UminusExpression uminusExpressionNode, P param);
    T visit(DoubleConst doubleConstNode, P param);
    T visit(IntegerConst integerConstNode, P param);
    T visit(StringConst stringConstNode, P param);
    T visit(NotExpression notExpressionNode, P param);
    T visit(TrueExpression trueExpressionNode, P param);
    T visit(FalseExpression falseExpressionNode, P param);
    T visit(RelationalExpression relationalExpressionNode, P param);
    T visit(AssignStatement assignStatementNode, P param);

}