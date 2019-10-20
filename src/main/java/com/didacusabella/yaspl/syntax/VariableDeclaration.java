package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;


import java.util.List;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Represent a variable declaration node. For example:
 * { @code int a; }
 */
public class VariableDeclaration extends Decl {

  private final TypeDenoter type;
  private final List<Variable> variables;

  /**
   * {@inheritDoc}
   * @param type the type node
   * @param variables the variable node list
   */
  public VariableDeclaration(Location leftLocation, Location rightLocation,
          TypeDenoter type, List<Variable> variables) {
    super(leftLocation, rightLocation);
    this.type = type;
    this.variables = variables;
  }

  public TypeDenoter getType() {
    return this.type;
  }

  public List<Variable> getVariables() {
    return this.variables;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
  
}
