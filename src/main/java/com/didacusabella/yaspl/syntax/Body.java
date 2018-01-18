package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java.util.List;

public class Body extends YasplTree {


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<VariableDeclaration> getVariableDeclarationList() {
        return this.subTrees(VariableDeclaration.class);
    }

    public List<Statement> getStatements() {
        return this.subTrees(Statement.class);
    }
}
