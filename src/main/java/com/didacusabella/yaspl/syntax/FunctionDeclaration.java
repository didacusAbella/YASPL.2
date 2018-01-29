package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.semantic.Scope;
import com.didacusabella.yaspl.semantic.Scopeable;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

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
public class FunctionDeclaration extends Decl implements Scopeable {

    private final Identifier identifier;
    private final List<VariableDeclaration> variableDeclarations;
    private final List<ParameterDeclaration> parameterDeclarations;
    private final Body body;
    private Scope scopeReference;

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


    @Override
    public boolean checkType() {
        return checkAll(variableDeclarations) && checkAll(parameterDeclarations) && body.checkType();
    }

    public String domainString(){
        StringJoiner sj = new StringJoiner("x");
        variableDeclarations.forEach(v -> {
            int size = v.getVariables().size();
            ReturnType type = v.getNodeType();
            String tmp = String.join("x", Collections.nCopies(size, type.getValue()));
            sj.add(tmp);
        });
        return sj.toString();
    }

    public String codomainString(){
        StringJoiner sj = new StringJoiner("x");
        parameterDeclarations.forEach(p -> {
            p.getVariableDeclarationList().forEach(v -> {
                int size = v.getVariables().size();
                ReturnType type = v.getNodeType();
                String tmp = String.join("x", Collections.nCopies(size, type.getValue()));
                sj.add(tmp);
            });
        });
        return sj.toString();
    }

    @Override
    public void attachScope(Scope table) {
        this.scopeReference = table;
    }

    @Override
    public Scope getAttachedScope() {
        return this.scopeReference;
    }
}
