package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.Collections;
import java.util.List;

/**
 * A composite statement is a list of statements
 * @since 1.0
 * @author didacusAbella
 */
public class CompositeStatement extends Statement {

    private final List<Statement> statements;

    public CompositeStatement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                              List<Statement> statements) {
        super(leftLocation, rightLocation);
        Collections.reverse(statements);
        this.statements = statements;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * A list of statement that create a composite statement
     * @return the list of statements
     */
    public List<Statement> getStatementList() {
        return this.statements;
    }
}
