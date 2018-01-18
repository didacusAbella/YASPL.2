package com.didacusabella.yaspl.syntax;

public abstract class RelationalExpression extends BooleanExpression {
    private BooleanExpression leftOperand;
    private BooleanExpression rightOperand;

    public BooleanExpression getLeftOperand() {
        return leftOperand;
    }

    public BooleanExpression getRightOperand() {
        return rightOperand;
    }
}
