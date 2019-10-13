package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is the node associated with a function call. For example:
 * <pre>
 *     {@code
 *     functionName(expressions:returnvars);
 *     }
 * </pre>
 */
public class FunctionCall extends Statement {

  private final Identifier identifier;
  private final List<Expression> expressions;
  private final List<Identifier> variables;

  /**
   * Create a new function call node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param identifier the function name
   * @param expressions the list of inputs
   * @param variables the list of outputs
   */
  public FunctionCall(Location leftLocation, Location rightLocation,
          Identifier identifier, List<Expression> expressions, List<Variable> variables) {
    super(leftLocation, rightLocation);
    this.identifier = identifier;
    this.expressions = expressions;
    this.variables = variables.stream().map(this.toIdentifier()).collect(Collectors.toList());
  }
  
  private Function<Variable, Identifier> toIdentifier(){
    return (Variable v) -> new Identifier(v.getLeftLocation(), v.getRightLocation(), v.getName());
  }

  /**
   * get the function name
   *
   * @return the function name
   */
  public Identifier getFunctionName() {
    return this.identifier;
  }

  /**
   * Get the list of the expression
   *
   * @return the list of the expresions
   */
  public List<Expression> getExpressions() {
    return this.expressions;
  }

  /**
   * get the list of the variables to return
   *
   * @return the list of variables
   */
  public List<Identifier> getVariables() {
    return this.variables;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
