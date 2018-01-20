package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

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
     * Create a new uminus expression
     * @param expression the expression to evaluate
     */
    public UminusExpression(Expression expression) {
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
