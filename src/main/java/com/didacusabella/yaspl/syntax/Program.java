package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class Program extends YasplTree {


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<VariableDeclaration> getVariableDeclarations()  {
       return this.subTrees(VariableDeclaration.class);
    }

    public List<FunctionDeclaration> getFunctionDeclarations() {
        return this.subTrees(FunctionDeclaration.class);
    }


}
