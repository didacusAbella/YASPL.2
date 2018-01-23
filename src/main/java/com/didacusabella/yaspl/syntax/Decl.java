package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory;

/**
 * A declaration is an abstraction for describe a Function Declaration or a Variable Declaration
 * @since 1.0
 * @author didacusAbella
 */
public abstract class Decl extends YasplNode {

    public Decl(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
        super(leftLocation, rightLocation);
    }
}
