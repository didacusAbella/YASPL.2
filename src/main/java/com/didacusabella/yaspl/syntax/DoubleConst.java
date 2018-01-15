package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class DoubleConst implements Expression {
    @Override
    public String getKind() {
        return "DOUBLE_CONST";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
