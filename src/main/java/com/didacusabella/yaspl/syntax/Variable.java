package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This node encapsulate a variable of a YASPL program. For example:
 * <pre>
 *     {@code
 *      a;
 *     }
 * </pre>
 */
public class Variable extends AstNode {

  private final String name;

  /**
   * Create a new variable node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param name the identifier node
   */
  public Variable(Location leftLocation, Location rightLocation, String name) {
    super(leftLocation, rightLocation);
    this.name = name;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  /**
   * Get the name of the variable
   *
   * @return the name of the variable
   */
  public String getName() {
    return this.name;
  }

}
