package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * A boolean expression is an expression that return always a boolean type as
 * return
 *
 * @since 1.0
 * @author didacusAbella
 */
public abstract class BooleanExpression extends Expression {

  /**
   * Create a new boolean expression node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   */
  public BooleanExpression(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return null;
  }

}
