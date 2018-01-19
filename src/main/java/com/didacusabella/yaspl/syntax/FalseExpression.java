package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

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
     * Create a new false expression wrapper
     */
    public FalseExpression() {
        super();
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
