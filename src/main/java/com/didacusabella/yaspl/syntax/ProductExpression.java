package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class ProductExpression extends BinaryExpression {

    @Override
    public String getKind() {
        return "MUL_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
