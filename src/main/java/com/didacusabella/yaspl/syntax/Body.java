package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.List;

/**
 * This is the body of a function. Is is composited by a bunch of statements and variables
 */
public class Body extends AstNode {

  private final List<VariableDeclaration> variableDeclarations;
  private final List<Statement> statements;

  /**
   * {@inheritDoc}
   * @param variableDeclarations the list of variable declarations
   * @param statements the statements list
   */
  public Body(Location leftLocation, Location rightLocation,
          List<VariableDeclaration> variableDeclarations, List<Statement> statements) {
    super(leftLocation, rightLocation);
    this.variableDeclarations = variableDeclarations;
    this.statements = statements;
  }
  
  public List<VariableDeclaration> getVariableDeclarations() {
    return this.variableDeclarations;
  }

  public List<Statement> getStatements() {
    return this.statements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
