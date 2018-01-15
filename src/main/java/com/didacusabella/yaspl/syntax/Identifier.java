package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class Identifier implements Expression {
    @Override
    public String getKind() {
        return "INDENTIFIER";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
