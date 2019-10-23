package com.didacusabella.yaspl.syntax;
import com.didacusabella.yaspl.type.CompositeType;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java.util.List;
import com.didacusabella.yaspl.visitor.Visitor;
import java.util.ArrayList;


/**
 * Node for function declaration. For example:
 * { @code 
 *    def functionName(type variblelist) :types variables {
 *        body
 *    }
 * }
 */
public class FunctionDeclaration extends Decl {

  private final Variable name;
  private final List<VariableDeclaration> inputs;
  private final List<ParameterDeclaration> outputs;
  private final Body body;

  /**
   * {@inheritDoc}
   * @param name the function name
   * @param inputs the list of input
   * @param outputs the list of outputs
   * @param body the body of the function
   */
  public FunctionDeclaration(Location leftLocation, Location rightLocation,
          Variable name, List<VariableDeclaration> inputs,
          List<ParameterDeclaration> outputs, Body body) {
    super(leftLocation, rightLocation);
    this.name = name;
    this.inputs = inputs;
    this.outputs = outputs;
    this.body = body;
  }

  public List<VariableDeclaration> getInputs() {
    return this.inputs;
  }

  public List<ParameterDeclaration> getOutputs() {
    return this.outputs;
  }

  public Variable getName() {
    return this.name;
  }

  public Body getBody() {
    return body;
  }
  
  public boolean haveArgs() {
    return this.outputs.size() > 0;
  }
  
  public CompositeType domain() {
    CompositeType ct = new CompositeType(new ArrayList<>());
    this.inputs.forEach(vd -> ct.addType(vd.getType().typeFactory()));
    return ct;
  }
  
  public CompositeType codomain() {
    CompositeType ct = new CompositeType(new ArrayList<>());
    this.outputs.forEach(pd -> ct.addType(pd.getType().typeFactory()));
    return ct;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
