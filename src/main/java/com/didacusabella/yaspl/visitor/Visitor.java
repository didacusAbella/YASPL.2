package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;

/**
 * Abstract visitor. T type is used for return type of the visitor. P type used for pass additional parameter. Use Void
 * for pass nothing or return nothing
 */
public interface Visitor<T, P> {
    /**
     * Visit for the program node
     * @param programNode the program node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(Program programNode, P param);

    /**
     * Visit for the VariableDeclaration node
     * @param variableDeclarationNode the node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(VariableDeclaration variableDeclarationNode, P param);

    /**
     * Visit for the FunctionDeclaration node
     * @param functionDeclarationNode the function node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(FunctionDeclaration functionDeclarationNode, P param);

    /**
     * Visit for the Variable node
     * @param variableNode the variable node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(Variable variableNode, P param);

    /**
     * Visit for Type node
     * @param typeNode the type node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(Type typeNode, P param);

    /**
     * Visit for Identifier node
     * @param identifierNode the identifier node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(Identifier identifierNode, P param);

    /**
     * Visit for ParameterDeclaration node
     * @param parameterDeclarationNode the parameter node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(ParameterDeclaration parameterDeclarationNode, P param);

    /**
     * Visit for Body node
     * @param bodyNode the body node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(Body bodyNode, P param);

    /**
     * Visit for ReadStatement node
     * @param readStatementNode the read node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(ReadStatement readStatementNode, P param);

    /**
     * Visit for WriteStatement node
     * @param writeStatementNode the write node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(WriteStatement writeStatementNode, P param);

    /**
     * Visit for FunctionCallNode
     * @param functionCallNode the function call node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(FunctionCall functionCallNode, P param);

    /**
     * Visit for CompositeStatement node
     * @param compositeStatementNode the composite node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(CompositeStatement compositeStatementNode, P param);

    /**
     * Visit for WhileStatement node
     * @param whileStatementNode the while node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(WhileStatement whileStatementNode, P param);

    /**
     * Visit for IfThenStatement node
     * @param ifThenStatementNode the if node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(IfThenStatement ifThenStatementNode, P param);

    /**
     * Visit for IfThenElseStatement node
     * @param ifThenElseStatementNode the if else node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(IfThenElseStatement ifThenElseStatementNode, P param);

    /**
     * Visit for BinaryExpression node
     * @param binaryExpressionNode the binary node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(BinaryExpression binaryExpressionNode, P param);

    /**
     * Visit for UminusExpression
     * @param uminusExpressionNode the uminus node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(UminusExpression uminusExpressionNode, P param);

    /**
     * Visit for DoubleConst node
     * @param doubleConstNode the double node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(DoubleConst doubleConstNode, P param);

    /**
     * Visit for IntegerConst node
     * @param integerConstNode the int node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(IntegerConst integerConstNode, P param);

    /**
     * Visit for the StringConst node
     * @param stringConstNode the string node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(StringConst stringConstNode, P param);

    /**
     * Visit for NotExpression node
     * @param notExpressionNode the not node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(NotExpression notExpressionNode, P param);

    /**
     * Visit for TrueExpression node
     * @param trueExpressionNode the true node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(TrueExpression trueExpressionNode, P param);

    /**
     * Visit for FalseExpression node
     * @param falseExpressionNode the false node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(FalseExpression falseExpressionNode, P param);

    /**
     * Visit for RelationalExpression node
     * @param relationalExpressionNode the relational node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(RelationalExpression relationalExpressionNode, P param);

    /**
     * Visit for AssingStatement node
     * @param assignStatementNode the assign node
     * @param param additional parameter
     * @return a custom type
     */
    T visit(AssignStatement assignStatementNode, P param);

}