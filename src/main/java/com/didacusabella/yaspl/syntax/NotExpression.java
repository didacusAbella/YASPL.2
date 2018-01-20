package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node represent the not expression. For example
 * <pre>
 *     {@code
 *     int a = 2;
 *     not (a == 1);
 *     }
 * </pre>
 */
public class NotExpression extends BooleanExpression {

    private final Expression expression;

    /**
     * Create a new not expression node
     * @param expression the expression to be evaluated
     */
    public NotExpression(Expression expression) {
        this.expression = expression;
    }


    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * get the expression associated with this node
     * @return the expression
     */
    public Expression getExpression(){
        return this.expression;
    }
}
