package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class WhileStatement extends Statement {


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return null;
    }

    public BooleanExpression getWhileCondition() {
        return this.subTree(BooleanExpression.class);
    }

    public Statement getWhileStatement() {
        return this.subTree(Statement.class);
    }
}
