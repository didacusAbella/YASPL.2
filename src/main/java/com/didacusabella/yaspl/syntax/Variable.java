package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

public class Variable extends YasplTree {

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public Identifier getIdentifier() {
        return this.subTree(Identifier.class);
    }


}
