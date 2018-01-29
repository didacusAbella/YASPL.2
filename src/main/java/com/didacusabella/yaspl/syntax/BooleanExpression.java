package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
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

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return null;
    }

}
