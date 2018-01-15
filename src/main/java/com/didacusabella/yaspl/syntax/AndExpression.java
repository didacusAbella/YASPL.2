package com.didacusabella.yaspl.syntax;


import com.didacusabella.yaspl.visitor.Visitor;

public class AndExpression implements BooleanExpression {
    @Override
    public String getKind() {
        return "AND_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
