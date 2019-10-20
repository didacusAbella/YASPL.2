package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node used for wrapping a string constant value. For example
 * { @code "Hello"; }
 */
public class StringConst extends Expression implements Leaf<String> {

  private final String stringValue;

  /**
   * {@inheritDoc}
   * @param stringValue the string value
   */
  public StringConst(Location leftLocation, Location rightLocation,
          String stringValue) {
    super(leftLocation, rightLocation);
    this.stringValue = stringValue;
  }
  
  @Override
  public String getValue() {
    return this.stringValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
