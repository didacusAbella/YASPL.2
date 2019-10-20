package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * A declaration is an abstraction for describe a Function Declaration or a
 * Variable Declaration
 */
public abstract class Decl extends AstNode {

  /**
   * {@inheritDoc}
   */
  public Decl(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }
}
