package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class VariableDeclaration implements YasplNode {

    private Type type;
    private List<Variable> variables;
    @Override
    public String getKind() {
        return "VAR_DECL_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
