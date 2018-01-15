package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class BooleanType implements Type {
    @Override
    public String getKind() {
        return "BOOLEAN_TYPE";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
