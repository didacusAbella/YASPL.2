package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class CompositeStatement extends Statement {


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<Statement> getStatementList() {
        return this.subTrees(Statement.class);
    }

}
