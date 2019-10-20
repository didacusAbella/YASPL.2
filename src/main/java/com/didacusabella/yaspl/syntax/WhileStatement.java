package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This is a while statement node. For example:
 * { @code
 *     while(booleanExpression) do {
 *       statements;
 *     };
 * }
 */
public class WhileStatement extends Statement {

  private final BooleanExpression condition;
  private final CompositeStatement whileStatement;

  /**
   * {@inheritDoc}
   * @param condition the condition for iterating in the while
   * @param whileStatement the while statement
   */
  public WhileStatement(Location leftLocation, Location rightLocation,
          BooleanExpression condition, CompositeStatement whileStatement) {
    super(leftLocation, rightLocation);
    this.condition = condition;
    this.whileStatement = whileStatement;
  }
  
  public BooleanExpression getCondition() {
    return this.condition;
  }

  
  public CompositeStatement getBody() {
    return this.whileStatement;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
  
}
