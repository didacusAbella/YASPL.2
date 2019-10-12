package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Node for wrapping integer const value. For example
 * <pre>
 *     {@code
 *     1
 *     }
 * </pre>
 */
public class IntegerConst extends Expression {

  private final int intValue;

  /**
   * Create a new integer node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param intValue the value of the node
   */
  public IntegerConst(Location leftLocation, Location rightLocation, int intValue) {
    super(leftLocation, rightLocation);
    this.intValue = intValue;
  }

  /**
   * get the int value associated with this node
   *
   * @return the int value
   */
  public int getIntValue() {
    return intValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
