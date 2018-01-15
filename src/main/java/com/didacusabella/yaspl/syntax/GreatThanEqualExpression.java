package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class GreatThanEqualExpression extends RelationalExpression {
    @Override
    public String getKind() {
        return "GE_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
