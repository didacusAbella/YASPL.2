package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java.util.List;

/**
 * This node represent the parameter declaration into a function. For example
 *{ @code
 *     def functionName(int a) : int x
 *}
 * The declarations after the colons are the parameters
 */
public class ParameterDeclaration extends AstNode {

  private final List<Variable> variables;
  private final TypeDenoter type;

  /**
   * {@inheritDoc}
   * @param variableDeclaration the variable declaration node
   */
  public ParameterDeclaration(Location leftLocation, Location rightLocation,
          VariableDeclaration variableDeclaration) {
    super(leftLocation, rightLocation);
    this.variables = variableDeclaration.getVariables();
    this.type = variableDeclaration.getType();
  }
  
  public List<Variable> getVariables() {
    return variables;
  }

  
  public TypeDenoter getType() {
    return type;
  }  

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
