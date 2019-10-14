package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.type.PrimitiveType;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This node wrapping the primitive types of a YASPL program.
 *
 * @since 1.0
 * @author didacusAbella
 */
public class TypeDenoter extends AstNode {

  private final String kind;

  /**
   * Create a new Type node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param kind the type name
   */
  public TypeDenoter(Location leftLocation, Location rightLocation, String kind) {
    super(leftLocation, rightLocation);
    this.kind = kind;
  }

  /**
   * return the string representation of this type
   * @return the kind of type
   */
  public String getKind() {
    return this.kind;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

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
}
