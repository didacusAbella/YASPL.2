package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class LessThanExpression extends RelationalExpression {
    @Override
    public String getKind() {
        return "LT_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
