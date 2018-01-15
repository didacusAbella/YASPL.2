package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IfThenStatement implements Statement {

    private BooleanExpression ifCondition;
    private Statement thenStatement;
    @Override
    public String getKind() {
        return null;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
