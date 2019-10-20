package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This node represent the root of a YASPL program
 */
public class Program extends AstNode {

  private final List<Decl> declarations;
  private final List<Statement> statements;

  /**
   * {@inheritDoc}
   * @param declarations the list of declarations
   * @param statements the list of statements
   */
  public Program(Location leftLocation, Location rightLocation,
          List<Decl> declarations, List<Statement> statements) {
    super(leftLocation, rightLocation);
    this.declarations = declarations;
    this.statements = statements;
  }

  
  public List<Decl> getDeclarations() {
    return declarations;
  }

  
  public List<Statement> getStatements() {
    return statements;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
