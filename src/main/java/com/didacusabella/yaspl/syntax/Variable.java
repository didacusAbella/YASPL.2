package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class Variable extends YasplNode {

    private final Identifier identifier;

    public Variable(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }


}
