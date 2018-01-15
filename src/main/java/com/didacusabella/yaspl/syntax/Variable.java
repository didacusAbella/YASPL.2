package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class Variable implements YasplNode {

    private Identifier identifier;

    @Override
    public String getKind() {
        return "VAR_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
