package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.type.PrimitiveType;
import com.didacusabella.yaspl.type.Type;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * An Expression's Node is an operation that return always a value.
 *
 */
public abstract class Expression extends AstNode {
  
  private Type type;

  /**
   * {@inheritDoc}
   */
  public Expression(Location leftLocation, Location rightLocation) {
    super(leftLocation, rightLocation);
    this.type = PrimitiveType.NULL;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
  
}
