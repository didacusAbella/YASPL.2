package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

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

    public ParameterDeclaration(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                                List<VariableDeclaration> variableDeclarations) {
        super(leftLocation, rightLocation);
        this.variableDeclarations = variableDeclarations;
    }

    /**
     * Create a new parameter declaration node
     * @param vd the single variable
     */
    public ParameterDeclaration(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                                VariableDeclaration vd) {
        super(leftLocation, rightLocation);
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
