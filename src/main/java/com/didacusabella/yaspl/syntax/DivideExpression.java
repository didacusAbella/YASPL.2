package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class DivideExpression extends BinaryExpression {
    @Override
    public String getKind() {
        return "DIV_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
