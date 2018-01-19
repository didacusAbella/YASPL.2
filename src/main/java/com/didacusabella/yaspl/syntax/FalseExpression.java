package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class FalseExpression extends BooleanExpression {


    public FalseExpression() {
        super();
    }

    public boolean getValue(){
        return false;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
