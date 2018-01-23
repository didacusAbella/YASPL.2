package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory;

/**
 * Abstract class that represents a generic statement into a YASPL program.
 */
public abstract class Statement extends YasplNode {

    public Statement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }
}
