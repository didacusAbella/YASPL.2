package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class FunctionDeclaration implements YasplNode {

    private Identifier identifier;
    private List<VariableDeclaration> variableDeclarationList;
    private List<ParameterDeclaration> paramenterDeclarationList;
    private Body body;

    @Override
    public String getKind() {
        return "PROC_DECL_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
