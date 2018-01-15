package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class GreatThanExpression extends RelationalExpression {
    @Override
    public String getKind() {
        return "GT_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
