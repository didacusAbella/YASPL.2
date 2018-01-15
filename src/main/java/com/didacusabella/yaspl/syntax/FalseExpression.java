package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class FalseExpression implements BooleanExpression {
    @Override
    public String getKind() {
        return "FALSE_LITERAL";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
