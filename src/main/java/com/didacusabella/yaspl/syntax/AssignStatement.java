package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class AssignStatement extends Statement {

    public Identifier getIdentifier() {
        return this.subTree(Identifier.class);
    }

    public Expression getExpression() {
        return this.subTree(Expression.class);
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
