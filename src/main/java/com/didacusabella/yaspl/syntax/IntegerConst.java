package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node for wrapping integer const value. For example
 * <pre>
 *     {@code
 *     1
 *     }
 * </pre>
 */
public class IntegerConst extends Expression {

    private final int intValue;

    /**
     * Create a new integer node
     * @param intValue the int value associated
     */
    public IntegerConst(int intValue) {
        this.intValue = intValue;
    }

    /**
     * get the int value associated with this node
     * @return the int value
     */
    public int getIntValue() {
        return intValue;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
