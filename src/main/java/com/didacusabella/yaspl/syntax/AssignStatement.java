package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Node that represents the assignment operation. Example:
 * {@code a = 1;}
 */
public class AssignStatement extends Statement {

  private final Identifier left;
  private final Expression right;

  /**
   * {@inheritDoc}
   * @param left left side of assignment
   * @param  right side of assignment
   */
  public AssignStatement(Location leftLocation, Location rightLocation,
          Identifier left, Expression right) {
    super(leftLocation, rightLocation);
    this.left= left;
    this.right = right;
  }

  public Identifier getLeft() {
    return this.left;
  }

  public Expression getRight() {
    return this.right;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
