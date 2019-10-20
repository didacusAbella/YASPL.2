package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node for wrapping integer const value. For example
 * { @code 1 }
 */
public class IntegerConst extends Expression implements Leaf<Integer> {

  private final int intValue;

  /**
   * {@inheritDoc}
   * @param intValue the value of the node
   */
  public IntegerConst(Location leftLocation, Location rightLocation, int intValue) {
    super(leftLocation, rightLocation);
    this.intValue = intValue;
  }

  @Override
  public Integer getValue() {
    return this.intValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

  
}
