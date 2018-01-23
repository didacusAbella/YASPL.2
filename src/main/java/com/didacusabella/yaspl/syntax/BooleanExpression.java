package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory;

/**
 * A boolean expression is an expression that return always a boolean type as return
 * @since 1.0
 * @author didacusAbella
 */
public abstract class BooleanExpression extends Expression {

    public BooleanExpression(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }
}
