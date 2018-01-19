package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This class handle binary expressions as sum, difference, divide, product
 */
public class BinaryExpression extends Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;
    private final String op;

    public BinaryExpression(Expression leftOperand, Expression rightOperand, String op) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
