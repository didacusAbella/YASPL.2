package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IntegerConst implements Expression {
    @Override
    public String getKind() {
        return "INT_CONST";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
