package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class TrueExpression implements BooleanExpression {
    @Override
    public String getKind() {
        return "TRUE_LITERAL";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
