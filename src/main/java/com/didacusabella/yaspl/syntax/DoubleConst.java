package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * Class wrapping the constant literal form. For example:
 * <pre>
 *     {@code
 *     2.0
 *     }
 * </pre>
 * @since 1.0
 * @author didacusAbella
 */
public class DoubleConst extends Expression {

    private final double doubleValue;

    public DoubleConst(ComplexSymbolFactory.Location leftLocation,
                       ComplexSymbolFactory.Location rightLocation, double doubleValue) {
        super(leftLocation, rightLocation);
        this.doubleValue = doubleValue;
    }

    /**
     * Return the double value associated with this wrapper
     * @return
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    @Override
    public boolean checkType() {
        return true;
    }
}
