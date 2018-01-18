package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class WriteStatement extends Statement {



    public List<Expression> getExpressionList() {
        return this.subTrees(Expression.class);
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
