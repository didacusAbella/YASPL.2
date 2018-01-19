package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Need not
 */
public class NotExpression extends BooleanExpression {

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public Expression getExpression(){
        return this.expression;
    }
}
