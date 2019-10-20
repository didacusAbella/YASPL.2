package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.type.PrimitiveType;
import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node wrapping the primitive types of a YASPL program.
 *
 */
public class TypeDenoter extends AstNode implements Leaf<String> {

  private final String kind;

  /**
   * {@inheritDoc}
   * @param kind the type name
   */
  public TypeDenoter(Location leftLocation, Location rightLocation, String kind) {
    super(leftLocation, rightLocation);
    this.kind = kind;
  }

  /**
   * Create the PrimitiveType associated with this instance
   * @return the primitive type
   */
  public PrimitiveType typeFactory(){
    switch(this.kind){
      case "int":
        return PrimitiveType.INT;
      case "double":
        return PrimitiveType.DOUBLE;
      case "string":
        return PrimitiveType.STRING;
      case "bool":
        return PrimitiveType.BOOL;
      default:
        return PrimitiveType.NULL;
    }
  }

  @Override
  public String getValue() {
    return this.kind;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
