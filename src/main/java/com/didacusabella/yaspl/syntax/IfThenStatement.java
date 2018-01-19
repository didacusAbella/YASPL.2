package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IfThenStatement extends Statement {

    private final BooleanExpression ifCondition;
    private final CompositeStatement thenStatement;

    public IfThenStatement(BooleanExpression ifCondition, CompositeStatement thenStatement) {
        this.ifCondition = ifCondition;
        this.thenStatement = thenStatement;
    }

    public BooleanExpression getIfCondition() {
        return this.ifCondition;
    }

    public Statement getThenStatement() {
        return this.thenStatement;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
