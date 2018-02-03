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

    public String variableDomain(){
        return this.variables.stream().map(v -> v.getIdentifier().getNodeType().getValue()).collect(Collectors.joining("X"));
    }

    public String typeDomain(){
        return this.types.stream().map(v -> v.getTypeName()).collect(Collectors.joining("X"));
    }

    public boolean checkInputValidity(){
        return this.variableDomain().equals(typeDomain());
    }
}
