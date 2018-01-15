package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class CompositeStatement implements Statement {
    private List<Statement> statementList;

    @Override
    public String getKind() {
        return "COMP_STAT_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
