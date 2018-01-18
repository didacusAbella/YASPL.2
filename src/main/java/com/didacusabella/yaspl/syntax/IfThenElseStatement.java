package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IfThenElseStatement extends Statement {


    public BooleanExpression getIfCondition() {
        return this.subTree(BooleanExpression.class);
    }

    public Statement getThenStatement() {
        return this.subTree(Statement.class);
    }

    public Statement getElseStatement() {
        return this.subTree(Statement.class);
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
