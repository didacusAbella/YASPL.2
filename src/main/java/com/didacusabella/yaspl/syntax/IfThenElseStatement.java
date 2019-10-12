package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * Create an if then else node statement. For example:
 * <pre>
 *     {@code
 *     if(booleanExpression) then {
 *         statements;
 *     }else {
 *         statements
 *     };
 *     }
 * </pre>
 */
public class IfThenElseStatement extends Statement {

  private final BooleanExpression ifCondition;
  private final CompositeStatement thenStatement;
  private final CompositeStatement elseStatement;

  /**
   * Create a new if then else statement node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
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

  /**
   * get the if condition
   *
   * @return the if condition
   */
  public BooleanExpression getIfCondition() {
    return this.ifCondition;
  }

  /**
   * get the then body
   *
   * @return the then body
   */
  public Statement getThenStatement() {
    return this.thenStatement;
  }

  /**
   * get the else body
   *
   * @return the else body
   */
  public Statement getElseStatement() {
    return this.elseStatement;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
