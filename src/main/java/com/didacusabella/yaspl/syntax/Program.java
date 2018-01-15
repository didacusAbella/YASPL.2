package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class Program implements YasplNode {

    private List<VariableDeclaration> variableDeclarations;
    private List<FunctionDeclaration> functionDeclarations;


    @Override
    public String getKind() {
        return "PROGRAM_OP";
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
