package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * This node represent the uminus expression. For example:
 * <pre>
 *     {@code
 *     -1
 *     }
 * </pre>
 */
public class UminusExpression extends Expression {

    private final Expression expression;

    /**
     * Create a new uminus node
     * @param leftLocation the left location
     * @param rightLocation the right location
     * @param expression the expression node
     */
    public UminusExpression(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                            Expression expression) {
        super(leftLocation, rightLocation);
        this.expression = expression;
    }

    /**
     * Get the expression associated
     * @return the expression
     */
    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
