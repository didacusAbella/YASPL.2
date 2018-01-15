package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class NotExpression implements BooleanExpression {
    @Override
    public String getKind() {
        return "NOT_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
