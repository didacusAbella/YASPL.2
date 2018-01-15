package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class EqualsExpression extends RelationalExpression {
    @Override
    public String getKind() {
        return "EQ_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
