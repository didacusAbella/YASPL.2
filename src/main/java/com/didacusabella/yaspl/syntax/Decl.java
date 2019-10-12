package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * A declaration is an abstraction for describe a Function Declaration or a
 * Variable Declaration
 *
 * @since 1.0
 * @author didacusAbella
 */
public abstract class Decl extends AstNode {

  /**
   * Create a new declaration
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   */
  public Decl(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }
}
