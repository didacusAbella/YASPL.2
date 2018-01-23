package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory;

/**
 * An Expression is an operation that return always a value
 * @since 1.0
 * @author didacusAbella
 */
public abstract class Expression extends YasplNode {

    public Expression(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }
}
