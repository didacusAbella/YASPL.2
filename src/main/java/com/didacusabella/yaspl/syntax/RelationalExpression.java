package com.didacusabella.yaspl.syntax;

public abstract class RelationalExpression implements BooleanExpression {
    private BooleanExpression leftOperand;
    private BooleanExpression rightOperand;
}
