package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class DivideExpression extends BinaryExpression {

    public DivideExpression(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
