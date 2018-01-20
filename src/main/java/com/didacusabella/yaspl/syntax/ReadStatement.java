package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

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
     * Create a new read statement
     * @param variables the list of variable to fill
     * @param types the types to fil variables
     */
    public ReadStatement(List<Variable> variables, List<Type> types) {
        this.variables = variables;
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
}
