package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class FunctionCall implements Statement {

    private Identifier functionName;
    private List<Expression> expressions;
    private List<Identifier> identifierList;
    @Override
    public String getKind() {
        return "CALL_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
