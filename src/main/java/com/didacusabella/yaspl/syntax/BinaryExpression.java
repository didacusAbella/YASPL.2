package com.didacusabella.yaspl.syntax;

public abstract class BinaryExpression implements Expression{

    private Expression leftOperand;
    private Expression rightOperand;

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(Expression leftOperand) {
        this.leftOperand = leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(Expression rightOperand) {
        this.rightOperand = rightOperand;
    }
}
