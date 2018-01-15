package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class ReadStatement implements Statement {
    private List<Identifier> identifierList;
    private List<Type> typeList;

    @Override
    public String getKind() {
        return "READ_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
