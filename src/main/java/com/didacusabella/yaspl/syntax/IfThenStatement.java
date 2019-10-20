package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node representing if then statement. For example:
 *{ @code
 *     if(booleanCondition) then {
 *         statements
 *     };
 *}
 */
public class IfThenStatement extends Statement {

  private final BooleanExpression ifCondition;
  private final CompositeStatement thenStatement;

  /**
   * {@inheritDoc}
   * @param ifCondition the if condition node
   * @param thenStatement the then statement node
   */
  public IfThenStatement(Location leftLocation, Location rightLocation,
          BooleanExpression ifCondition, CompositeStatement thenStatement) {
    super(leftLocation, rightLocation);
    this.ifCondition = ifCondition;
    this.thenStatement = thenStatement;
  }

  
  public BooleanExpression getIfCondition() {
    return this.ifCondition;
  }

  
  public Statement getThenStatement() {
    return this.thenStatement;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
