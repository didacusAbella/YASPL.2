package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.List;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * A composite statement is a list of statements
 *
 */
public class CompositeStatement extends Statement {

  private final List<Statement> statements;

  /**
   * {@inheritDoc}
   * @param statements the list of statements
   */
  public CompositeStatement(Location leftLocation, Location rightLocation,
          List<Statement> statements) {
    super(leftLocation, rightLocation);
    this.statements = statements;
  }

  public List<Statement> getStatements() {
    return this.statements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
