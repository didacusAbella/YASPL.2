package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.List;

/**
 * This node represent the root of a YASPL program
 */
public class Program extends YasplNode {

    private final List<Decl> declarations;
    private final List<Statement> statements;

    public Program(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                   List<Decl> declarations, List<Statement> statements) {
        super(leftLocation, rightLocation);
        this.declarations = declarations;
        this.statements = statements;
    }

    /**
     * Get the list of all declarations
     * @return the list of declarations
     */
    public List<Decl> getDeclarations() {
        return declarations;
    }

    /**
     * Get the list of all statement
     * @return the list of all statements
     */
    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }



}
