package com.didacusabella.yaspl.syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This is the node associated with a function call. For example:
 * {@code functionName(expressions:returnvars);}
 */
public class FunctionCall extends Statement {

  private final Identifier name;
  private final List<Expression> inputs;
  private final List<Identifier> outputs;

  /**
   * {@inheritDoc}
   * @param name the function name
   * @param inputs the list of inputs
   * @param outputs the list of outputs
   */
  public FunctionCall(Location leftLocation, Location rightLocation,
          Identifier name, List<Expression> inputs, List<Variable> outputs) {
    super(leftLocation, rightLocation);
    this.name = name;
    this.inputs = inputs;
    this.outputs = outputs.stream().map(this.toIdentifier()).collect(Collectors.toList());
  }
  
  private Function<Variable, Identifier> toIdentifier(){
    return (Variable v) -> new Identifier(v.getLeftLocation(), v.getRightLocation(), v.getValue());
  }

  
  public Identifier getName() {
    return this.name;
  }

  public List<Expression> getInputs() {
    return this.inputs;
  }

  public List<Identifier> getOutputs() {
    return this.outputs;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }
}
