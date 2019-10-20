package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node for variable used as expression. For example
 * {@code a = b + c;}
 */
public class Identifier extends Expression implements Leaf<String> {

  private final String name;

  /**
   * {@inheritDoc}
   * @param name the name of the identifier
   */
  public Identifier(Location leftLocation, Location rightLocation, String name) {
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
