package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class NotExpression extends BooleanExpression {

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public Expression getExpression(){
        return this.subTree(Expression.class);
    }
}
