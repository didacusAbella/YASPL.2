package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class OrExpression extends RelationalExpression {
    @Override
    public String getKind() {
        return "OR_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
