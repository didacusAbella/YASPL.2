package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class LessThanEqualExpression extends RelationalExpression {
    @Override
    public String getKind() {
        return "LE_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
