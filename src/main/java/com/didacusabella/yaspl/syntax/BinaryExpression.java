package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Node for a BinaryExpression. Is used for handle mathematical operations. For example:
 * {@code 2+2;}
 */
public class BinaryExpression extends Expression {

  private final Expression leftOperand;
  private final Expression rightOperand;
  private final String op;

  /**
   * 
   * {@inheritDoc}
   * @param leftOperand left side expression
   * @param rightOperand right side expression
   * @param op the operand's kind
   */
  public BinaryExpression(Location leftLocation, Location rightLocation,
          Expression leftOperand, Expression rightOperand, String op) {
    super(leftLocation, rightLocation);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.op = op;
  }

  public String getOp() {
    return op;
  }

  public Expression getLeftOperand() {
    return leftOperand;
  }

  public Expression getRightOperand() {
    return rightOperand;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

 
}
