package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.Collections;
import java.util.List;

/**
 * A composite statement is a list of statements
 *
 * @since 1.0
 * @author didacusAbella
 */
public class CompositeStatement extends Statement {

  private final List<Statement> statements;

  /**
   * Create a new composite statement cnode
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param statements the list of statements
   */
  public CompositeStatement(Location leftLocation, Location rightLocation,
          List<Statement> statements) {
    super(leftLocation, rightLocation);
    this.statements = statements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  /**
   * A list of statement that create a composite statement
   *
   * @return the list of statements
   */
  public List<Statement> getStatements() {
    return this.statements;
  }
}
