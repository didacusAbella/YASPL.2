package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

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
     * Create a new stirng const node
     * @param leftLocation the left location
     * @param rightLocation the right location
     * @param stringValue the string value
     */
    public StringConst(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                       String stringValue) {
        super(leftLocation, rightLocation);
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
