package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class WhileStatement implements Statement {

    private BooleanExpression whileCondition;
    private Statement whileStatement;

    @Override
    public String getKind() {
        return "WHILE_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
