package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node encapsulate a variable used for declaration 
 * { @code a; }
 */
public class Variable extends AstNode implements Leaf<String> {

  private final String name;

  /**
   * {@inheritDoc}
   * @param name the name of the variable
   */
  public Variable(Location leftLocation, Location rightLocation, String name) {
    super(leftLocation, rightLocation);
    this.name = name;
  }

  @Override
  public String getValue() {
    return this.name;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
