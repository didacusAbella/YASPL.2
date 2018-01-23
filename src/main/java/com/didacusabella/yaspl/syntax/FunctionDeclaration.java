package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.List;

/**
 * This class id the node for function declaration. For example:
 * <pre>
 *     {@code
 *     def functionName(type variblelist) :types variables {
 *         body
 *     }
 *     }
 * </pre>
 */
public class FunctionDeclaration extends Decl {

    private final Identifier identifier;
    private final List<VariableDeclaration> variableDeclarations;
    private final List<ParameterDeclaration> parameterDeclarations;
    private final Body body;

    public FunctionDeclaration(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                               Identifier identifier, List<VariableDeclaration> variableDeclarations,
                               List<ParameterDeclaration> parameterDeclarations, Body body) {
        super(leftLocation, rightLocation);
        this.identifier = identifier;
        this.variableDeclarations = variableDeclarations;
        this.parameterDeclarations = parameterDeclarations;
        this.body = body;
    }

    /**
     * get the list of formal parameters
     * @return the list of variables
     */
    public List<VariableDeclaration> getVariableDeclarations() {
        return variableDeclarations;
    }

    /**
     * get the list of return variables
     * @return the list of returned variables
     */
    public List<ParameterDeclaration> getParameterDeclarations() {
        return parameterDeclarations;
    }

    /**
     * get the name of the function
     * @return the name of the function
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * get the body of the function
     * @return the body of the function
     */
    public Body getBody() {
        return body;
    }

    @Override
    public <T, P> T accept(Visitor<T,P> visitor, P param) {
       return visitor.visit(this, param);
    }




}
