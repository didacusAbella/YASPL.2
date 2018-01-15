package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class UminusExpression implements Expression {
    @Override
    public String getKind() {
        return "UMINUS_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
