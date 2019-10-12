package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * A relational expression is a boolean expression used for relational
 * operators. For example:
 * <pre>
 *     {@code
 *     int a, b;
 *     a > b;
 *     }
 * </pre>
 */
public class RelationalExpression extends BooleanExpression {

  private final Expression leftOperand, rightOperand;
  private final String relOp;

  /**
   * Create a new relational expression node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
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

  /**
   * Get the left operand
   *
   * @return the expression associated to left operand
   */
  public Expression getLeftOperand() {
    return leftOperand;
  }

  /**
   * Get the right operand
   *
   * @return the expression associated to right operand
   */
  public Expression getRightOperand() {
    return rightOperand;
  }

  /**
   * Get the relational operator
   *
   * @return the relational operator
   */
  public String getRelOp() {
    return relOp;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
