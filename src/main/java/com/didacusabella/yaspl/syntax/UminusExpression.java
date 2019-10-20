package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node represent the uminus expression. For example:
 * {@code -1 }
 */
public class UminusExpression extends Expression {

  private final Expression expression;

  /**
   * {@inheritDoc}
   * @param expression the expression node
   */
  public UminusExpression(Location leftLocation, Location rightLocation,
          Expression expression) {
    super(leftLocation, rightLocation);
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
