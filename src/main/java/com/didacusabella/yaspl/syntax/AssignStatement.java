package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class AssignStatement implements Statement {

    private Identifier identifier;
    private Expression expression;
    @Override
    public String getKind() {
        return "ASSIGN_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
