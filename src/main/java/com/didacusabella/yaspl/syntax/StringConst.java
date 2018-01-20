package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node used for wrapping a string constant value. For example
 * <pre>
 *     {@code
 *     "Hello";
 *     }
 * </pre>
 */
public class StringConst extends Expression {

    private String stringValue;

    /**
     * Create a new string wrapper
     * @param stringValue the string constant
     */
    public StringConst(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Get the string constant
     * @return the string constant
     */
    public String getStringValue() {
        return stringValue;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
