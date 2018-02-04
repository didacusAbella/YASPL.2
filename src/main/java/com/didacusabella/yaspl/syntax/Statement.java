package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory;

/**
 * Abstract class that represents a generic statement into a YASPL program.
 */
public abstract class Statement extends YasplNode {
    /**
     * Create a new statement node
     * @param leftLocation the left location
     * @param rightLocation the right location
     */
    public Statement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }
}
