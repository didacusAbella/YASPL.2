package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class WhileStatement extends Statement {

    private final BooleanExpression booleanExpression;
    private final CompositeStatement whileStatement;

    public WhileStatement(BooleanExpression booleanExpression, CompositeStatement whileStatement) {
        this.booleanExpression = booleanExpression;
        this.whileStatement = whileStatement;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public BooleanExpression getWhileCondition() {
        return this.booleanExpression;
    }

    public CompositeStatement getWhileStatement() {
        return this.whileStatement;
    }
}
