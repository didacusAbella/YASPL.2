package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node wrapping A double literal. For example:
 * {@code 2.0}
 */
public class DoubleConst extends Expression implements Leaf<Double> {

  private final double doubleValue;

  /**
   * {@inheritDoc}
   * @param doubleValue the literal double
   */
  public DoubleConst(Location leftLocation, Location rightLocation, double doubleValue) {
    super(leftLocation, rightLocation);
    this.doubleValue = doubleValue;
  }

  @Override
  public Double getValue() {
    return this.doubleValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}
