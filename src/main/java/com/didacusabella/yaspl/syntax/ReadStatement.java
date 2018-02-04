package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This node represents a Read statement. For example:
 * <pre>
 *     {@code
 *     x, y <- int, int;
 *     }
 * </pre>
 */
public class ReadStatement extends Statement {

    private final List<Variable> variables;
    private final List<Type> types;

    /**
     * Create a new read statement node
     * @param leftLocation the left location
     * @param rightLocation the right location
     * @param variables the list of variables in input
     * @param types the types to convert the variables
     */
    public ReadStatement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                         List<Variable> variables, List<Type> types) {
        super(leftLocation, rightLocation);
        Collections.reverse(variables);
        this.variables = variables;
        Collections.reverse(types);
        this.types = types;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * Return the identifier list
     * @return the identifier list
     */
    public List<Variable> getIdentifierList() {
        return this.variables;
    }

    /**
     * Get the types to assign to each variable
     * @return the types
     */
    public List<Type> getTypeList() {
        return this.types;
    }

    /**
     * Return a stirng representation of the domain of variables
     * @return the domain
     */
    public String variableDomain(){
        return this.variables.stream().map(v -> v.getIdentifier().getNodeType().getValue()).collect(Collectors.joining("X"));
    }

    /**
     * Return a stirng representation of the domain of types
     * @return the domain of the types
     */
    public String typeDomain(){
        return this.types.stream().map(v -> v.getTypeName()).collect(Collectors.joining("X"));
    }

    /**
     * check if the input domain and the type domain are compatible
     * @return true if the two domain are equal. False otherwise
     */
    public boolean checkInputValidity(){
        return this.variableDomain().equals(typeDomain());
    }
}
