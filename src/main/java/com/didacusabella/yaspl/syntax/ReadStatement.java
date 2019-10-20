package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This node represents a Read statement. For example:
 *{@code
 *     x, y <- int, int;
 *}
 */
public class ReadStatement extends Statement {

  private final List<Identifier> variables;
  private final List<TypeDenoter> types;

  /**
   * {@inheritDoc}
   * @param variables the list of variables in input
   * @param types the types to convert the variables
   */
  public ReadStatement(Location leftLocation, Location rightLocation,
          List<Variable> variables, List<TypeDenoter> types) {
    super(leftLocation, rightLocation);
    this.variables = variables.stream().map(this.toIdentifier()).collect(Collectors.toList());
    this.types = types;
  }
  
  private Function<Variable, Identifier> toIdentifier(){
    return (Variable v) -> new Identifier(v.getLeftLocation(), v.getRightLocation(), v.getValue());
  }

  public List<Identifier> getIdentifiers() {
    return this.variables;
  }

  public List<TypeDenoter> getTypes() {
    return this.types;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
