package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class DoubleType implements Type {
    @Override
    public String getKind() {
        return "DOUBLE_TYPE";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
