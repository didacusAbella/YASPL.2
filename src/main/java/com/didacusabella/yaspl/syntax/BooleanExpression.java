package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * A boolean expression is an expression that return always a boolean type
 */
public abstract class BooleanExpression extends Expression {

  /**
   * 
   * {@inheritDoc}
   */
  public BooleanExpression(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }
}
