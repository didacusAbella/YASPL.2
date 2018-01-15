package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class StringType implements Type {
    @Override
    public String getKind() {
        return "STRING_TYPE";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
