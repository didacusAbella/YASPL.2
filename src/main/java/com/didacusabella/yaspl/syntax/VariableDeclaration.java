package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

/**
 * Represent a variable declaration node. For example:
 * <pre>
 *     {@code
 *     int a;
 *     }
 * </pre>
 */
public class VariableDeclaration extends Decl {

    private final Type type;
    private final List<Variable> variables;

    /**
     * Create a new variable declaration node
     * @param type the type of the variable
     * @param variables the list of variables assoiciated with the type
     */
    public VariableDeclaration(Type type, List<Variable> variables) {
        this.type = type;
        this.variables = variables;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * Get the type of the declaration
     * @return the type of the declaration
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Get the list of the variable
     * @return the list of the variable
     */
    public List<Variable> getVariables() {
        return this.variables;
    }
}
