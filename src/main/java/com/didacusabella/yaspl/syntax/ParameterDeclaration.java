package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java.util.List;

/**
 * This node represent the parameter declaration into a function. For example
 * <pre>
 *     {@code
 *     def functionName(int a) : int x
 *     the values after the colon are the parameters
 *     }
 * </pre>
 */
public class ParameterDeclaration extends AstNode {

  private final List<Variable> variables;
  private final TypeDenoter type;

  /**
   * Create a new parameter declaration node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param variableDeclaration the variable declaration node
   */
  public ParameterDeclaration(Location leftLocation, Location rightLocation,
          VariableDeclaration variableDeclaration) {
    super(leftLocation, rightLocation);
    this.variables = variableDeclaration.getVariables();
    this.type = variableDeclaration.getType();
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  /**
   * Get the variable list
   * @return the variable list 
   */
  public List<Variable> getVariables() {
    return variables;
  }

  /**
   * Get the type denoter
   * @return the type denoter node
   */
  public TypeDenoter getType() {
    return type;
  }

}
