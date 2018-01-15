package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class WriteStatement implements Statement {
    private List<Expression> expressionList;

    @Override
    public String getKind() {
        return "WRITE_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
