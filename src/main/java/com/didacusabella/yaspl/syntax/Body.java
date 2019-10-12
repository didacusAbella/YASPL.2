package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.List;

/**
 * This is the body of a function. Is composite by a bunch of statements and
 * variables
 *
 * @since 1.0
 * @author didacusAbella
 */
public class Body extends AstNode {

  private final List<VariableDeclaration> variableDeclarations;
  private final List<Statement> statements;

  /**
   * Create a new body node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param variableDeclarations the list of variable declarations
   * @param statements the statements list
   */
  public Body(Location leftLocation, Location rightLocation,
          List<VariableDeclaration> variableDeclarations, List<Statement> statements) {
    super(leftLocation, rightLocation);
    this.variableDeclarations = variableDeclarations;
    this.statements = statements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  /**
   * The set of variable declarations
   *
   * @return the list of variable declarations
   */
  public List<VariableDeclaration> getVariableDeclarations() {
    return this.variableDeclarations;
  }

  /**
   * The set of statements
   *
   * @return the list of the statements
   */
  public List<Statement> getStatements() {
    return this.statements;
  }

}
