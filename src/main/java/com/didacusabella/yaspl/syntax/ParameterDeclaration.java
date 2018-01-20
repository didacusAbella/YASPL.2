package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.ArrayList;
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
public class ParameterDeclaration extends YasplNode {

    private final List<VariableDeclaration> variableDeclarations;

    /**
     * Create a new parameter declaration node
     * @param variableDeclarations the list of variables used as parameters
     */
    public ParameterDeclaration(List<VariableDeclaration> variableDeclarations) {
        this.variableDeclarations = variableDeclarations;
    }

    /**
     * Create a new parameter declaration node
     * @param vd the single variable
     */
    public ParameterDeclaration(VariableDeclaration vd) {
        this.variableDeclarations = new ArrayList<>();
        this.variableDeclarations.add(vd);
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * Get the list of variables used as parameter
     * @return the list of variables
     */
    public List<VariableDeclaration> getVariableDeclarationList() {
        return this.variableDeclarations;
    }

}
