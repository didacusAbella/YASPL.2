package com.didacusabella.yaspl.syntax;

public abstract class BinaryExpression extends Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;

    public BinaryExpression(Expression leftOperand, Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }
}
