package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * Tree node representing if then statement. For example:
 * <pre>
 *     {@code
 *     if(booleancondition) then {
 *         statements
 *     };
 *     }
 * </pre>
 */
public class IfThenStatement extends Statement {

    private final BooleanExpression ifCondition;
    private final CompositeStatement thenStatement;

    public IfThenStatement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                           BooleanExpression ifCondition, CompositeStatement thenStatement) {
        super(leftLocation, rightLocation);
        this.ifCondition = ifCondition;
        this.thenStatement = thenStatement;
    }

    /**
     * Get the if condition
     * @return the if condition
     */
    public BooleanExpression getIfCondition() {
        return this.ifCondition;
    }

    /**
     * Get the if's body
     * @return the body
     */
    public Statement getThenStatement() {
        return this.thenStatement;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
