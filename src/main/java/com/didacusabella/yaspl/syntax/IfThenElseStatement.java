package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IfThenElseStatement implements Statement {
    private BooleanExpression ifCondition;
    private Statement thenStatement;
    private Statement elseStatement;

    @Override
    public String getKind() {
        return "IF_THEN_ELSE_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
