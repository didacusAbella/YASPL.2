package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Class wrapping the constant literal form. For example:
 * <pre>
 *     {@code
 *     2.0
 *     }
 * </pre>
 *
 * @since 1.0
 * @author didacusAbella
 */
public class DoubleConst extends Expression {

  private final double doubleValue;

  /**
   * Create a new double node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param doubleValue the double value
   */
  public DoubleConst(Location leftLocation, Location rightLocation, double doubleValue) {
    super(leftLocation, rightLocation);
    this.doubleValue = doubleValue;
  }

  /**
   * Return the double value associated with this wrapper
   *
   * @return the double value associated with this node
   */
  public double getDoubleValue() {
    return doubleValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
