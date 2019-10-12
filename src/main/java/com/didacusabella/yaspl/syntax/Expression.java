package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * An Expression is an operation that return always a value
 *
 * @since 1.0
 * @author didacusAbella
 */
public abstract class Expression extends AstNode {

  /**
   * Create a new Expression node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   */
  public Expression(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }
}
