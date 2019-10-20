package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node represent the not expression. For example
 * { @code
 *     int a = 2;
 *     not (a == 1);
 * }
 */
public class NotExpression extends BooleanExpression {

  private final Expression expression;

  /**
   * {@inheritDoc}
   * @param expression the expression
   */
  public NotExpression(Location leftLocation, Location rightLocation,
          Expression expression) {
    super(leftLocation, rightLocation);
    this.expression = expression;
  }

  public Expression getExpression() {
    return this.expression;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
