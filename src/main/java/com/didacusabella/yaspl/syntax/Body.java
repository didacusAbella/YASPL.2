package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class Body implements YasplNode {
    private List<VariableDeclaration> variableDeclarationList;
    private List<Statement> statements;
    @Override
    public String getKind() {
        return "BODY_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
