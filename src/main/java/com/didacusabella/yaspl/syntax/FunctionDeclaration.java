package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class FunctionDeclaration extends YasplTree {


    @Override
    public <T, P> T accept(Visitor<T,P> visitor, P param) {
       return visitor.visit(this, param);
    }

    public Identifier getIdentifier() {
        return this.subTree(Identifier.class);
    }

    public List<VariableDeclaration> getVariableDeclarationList() {
        return this.subTrees(VariableDeclaration.class);
    }

    public List<ParameterDeclaration> getParamenterDeclarationList() {
        return this.subTrees(ParameterDeclaration.class);
    }

    public Body getBody() {
        return this.subTree(Body.class);
    }
}
