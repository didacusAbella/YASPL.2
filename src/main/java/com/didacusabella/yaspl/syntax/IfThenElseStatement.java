package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IfThenElseStatement extends Statement {

    private final BooleanExpression ifCondition;
    private final CompositeStatement thenStatement;
    private final CompositeStatement elseStatement;

    public IfThenElseStatement(BooleanExpression ifCondition,
                               CompositeStatement thenStatement, CompositeStatement elseStatement) {
        this.ifCondition = ifCondition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public BooleanExpression getIfCondition() {
        return this.ifCondition;
    }

    public Statement getThenStatement() {
        return this.thenStatement;
    }

    public Statement getElseStatement() {
        return this.elseStatement;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
