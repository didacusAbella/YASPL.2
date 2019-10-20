package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * A relational expression is a boolean expression used for relational
 * operators. For example:
 * {@code
 *     int a, b;
 *     a > b;
 *}
 */
public class RelationalExpression extends BooleanExpression {

  private final Expression leftOperand, rightOperand;
  private final String relOp;

  /**
   * {@inheritDoc}
   * @param leftOperand the left operand
   * @param rightOperand the right operand
   * @param relOp the operation
   */
  public RelationalExpression(Location leftLocation, Location rightLocation,
          Expression leftOperand, Expression rightOperand, String relOp) {
    super(leftLocation, rightLocation);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.relOp = relOp;
  }

  
  public Expression getLeftOperand() {
    return leftOperand;
  }

  
  public Expression getRightOperand() {
    return rightOperand;
  }

  
  public String getRelOp() {
    return relOp;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
