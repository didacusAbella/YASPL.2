package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Tree node representing if then statement. For example:
 * <pre>
 *     {@code
 *     if(booleanCondition) then {
 *         statements
 *     };
 *     }
 * </pre>
 */
public class IfThenStatement extends Statement {

  private final BooleanExpression ifCondition;
  private final CompositeStatement thenStatement;

  /**
   * Create a new if-then statement node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param ifCondition the if condition node
   * @param thenStatement the then statement node
   */
  public IfThenStatement(Location leftLocation, Location rightLocation,
          BooleanExpression ifCondition, CompositeStatement thenStatement) {
    super(leftLocation, rightLocation);
    this.ifCondition = ifCondition;
    this.thenStatement = thenStatement;
  }

  /**
   * Get the if condition
   *
   * @return the if condition
   */
  public BooleanExpression getIfCondition() {
    return this.ifCondition;
  }

  /**
   * Get the if's body
   *
   * @return the body
   */
  public Statement getThenStatement() {
    return this.thenStatement;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
