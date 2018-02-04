package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * This expression is a wrapper around false value
 * <pre>
 *     {@code
 *     false
 *     }
 * </pre>
 */
public class FalseExpression extends BooleanExpression {
    /**
     * Create a new false expression node
     * @param leftLocation the left location
     * @param rightLocation the right location
     */
    public FalseExpression(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }

    /**
     * Return the value of this wrapper
     * @return always false
     */
    public boolean getValue(){
        return false;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

}
