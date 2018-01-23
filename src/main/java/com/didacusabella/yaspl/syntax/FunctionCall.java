package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.List;

/**
 * This is the node associated with a function call. For example:
 * <pre>
 *     {@code
 *     functionName(expressions:returnvars);
 *     }
 * </pre>
 */
public class FunctionCall extends Statement {

    private final Identifier identifier;
    private final List<Expression> expressions;
    private final List<Variable> variables;

    public FunctionCall(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                        Identifier identifier, List<Expression> expressions, List<Variable> variables) {
        super(leftLocation, rightLocation);
        this.identifier = identifier;
        this.expressions = expressions;
        this.variables = variables;
    }

    /**
     * get the function name
     * @return the function name
     */
    public Identifier getFunctionName() {
        return this.identifier;
    }

    /**
     * Get the list of the expression
     * @return the list of the expresions
     */
    public List<Expression> getExpressions() {
        return this.expressions;
    }

    /**
     * get the list of the variables to return
     * @return the list of variables
     */
    public List<Variable> getVariableList() {
        return this.variables;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }


}
