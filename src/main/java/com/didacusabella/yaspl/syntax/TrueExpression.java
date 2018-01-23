package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * This expression is a wrapper around true value
 * <pre>
 *     {@code
 *     true
 *     }
 * </pre>
 */
public class TrueExpression extends BooleanExpression {

    public TrueExpression(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }

    /**
     * Return the value of this wrapper
     * @return always true
     */
    public boolean getValue(){
        return true;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
