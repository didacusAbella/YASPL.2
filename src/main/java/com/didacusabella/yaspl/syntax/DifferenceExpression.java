package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class DifferenceExpression extends BinaryExpression {
    @Override
    public String getKind() {
        return "DIFF_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
