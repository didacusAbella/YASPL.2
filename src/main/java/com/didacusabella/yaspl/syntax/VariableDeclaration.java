package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class VariableDeclaration extends YasplTree {


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public Type getType() {
        return this.subTree(Type.class);
    }

    public List<Variable> getVariables() {
        return this.subTrees(Variable.class);
    }
}
