package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 *
 * @author didacus
 */
public class BooleanConst extends BooleanExpression implements Leaf<Boolean> {
  
  private final boolean boolConst;

  public BooleanConst(Location leftLocation, Location rightLocation, boolean boolConst) {
    super(leftLocation, rightLocation);
    this.boolConst = boolConst;
  }

  @Override
  public Boolean getValue() {
    return this.boolConst;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
