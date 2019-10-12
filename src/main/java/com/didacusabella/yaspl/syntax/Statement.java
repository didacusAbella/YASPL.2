package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Abstract class that represents a generic statement into a YASPL program.
 */
public abstract class Statement extends AstNode {

  /**
   * Create a new statement node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   */
  public Statement(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }
}
