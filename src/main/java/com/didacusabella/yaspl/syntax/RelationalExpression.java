package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class RelationalExpression extends BooleanExpression {

    private final Expression leftOperand, rightOperand;
    private final String relOp;

    public RelationalExpression(Expression leftOperand, Expression rightOperand, String boolOp) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.relOp = boolOp;
    }

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }

    public String getRelOp() {
        return relOp;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
