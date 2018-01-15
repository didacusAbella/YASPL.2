package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class IntegerType implements Type {
    @Override
    public String getKind() {
        return "INTEGER_TYPE";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
