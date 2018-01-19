package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
/**
 * This expression is a wrapper around true value
 * <pre>
 *     {@code
 *     true
 *     }
 * </pre>
 */
public class TrueExpression extends BooleanExpression {

    /**
     * Create a new true expression wrapper
     */
    public TrueExpression() {
        super();
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
