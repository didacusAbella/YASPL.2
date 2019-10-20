package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Node for if then else statement. For example:
 *     {@code
 *     if(booleanExpression) then {
 *         statements;
 *     }else {
 *         statements
 *     };
 *     }
 */
public class IfThenElseStatement extends Statement {

  private final BooleanExpression ifCondition;
  private final CompositeStatement thenStatement;
  private final CompositeStatement elseStatement;

  /**
   * {@inheritDoc}
   * @param ifCondition the if condition node
   * @param thenStatement the then statement node
   * @param elseStatement the else statement node
   */
  public IfThenElseStatement(Location leftLocation, Location rightLocation,
          BooleanExpression ifCondition, CompositeStatement thenStatement,
          CompositeStatement elseStatement) {
    super(leftLocation, rightLocation);
    this.ifCondition = ifCondition;
    this.thenStatement = thenStatement;
    this.elseStatement = elseStatement;
  }

  
  public BooleanExpression getIfCondition() {
    return this.ifCondition;
  }

  public Statement getThenStatement() {
    return this.thenStatement;
  }

  public Statement getElseStatement() {
    return this.elseStatement;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
