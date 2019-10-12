package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This node wrapping the primitive types of a YASPL program.
 *
 * @since 1.0
 * @author didacusAbella
 */
public class TypeDenoter extends AstNode {

  private final int kind;

  /**
   * Create a new Type node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param kind the type name
   */
  public TypeDenoter(Location leftLocation, Location rightLocation, int kind) {
    super(leftLocation, rightLocation);
    this.kind = kind;
  }

  /**
   * return the int representation of this type
   * @return the kind of type
   */
  public int getKind() {
    return this.kind;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
