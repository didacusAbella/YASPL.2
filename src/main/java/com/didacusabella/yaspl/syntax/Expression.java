package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.type.PrimitiveType;
import com.didacusabella.yaspl.type.Type;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * An Expression is an operation that return always a value
 *
 * @since 1.0
 * @author didacusAbella
 */
public abstract class Expression extends AstNode {
  
  private Type type;

  /**
   * Create a new Expression node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
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
