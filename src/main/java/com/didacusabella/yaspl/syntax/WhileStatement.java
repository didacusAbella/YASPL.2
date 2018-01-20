package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This is a while statement node for a YASPL program. For example:
 * <pre>
 *     {@code
 *     while(booleanExpression) do {
 *       statements;
 *     };
 *     }
 * </pre>
 */
public class WhileStatement extends Statement {

    private final BooleanExpression booleanExpression;
    private final CompositeStatement whileStatement;

    /**
     * Create a new while statement
     * @param booleanExpression the boolean condition
     * @param whileStatement the while statement's list
     */
    public WhileStatement(BooleanExpression booleanExpression, CompositeStatement whileStatement) {
        this.booleanExpression = booleanExpression;
        this.whileStatement = whileStatement;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * Get the while condition
     * @return the while condition
     */
    public BooleanExpression getWhileCondition() {
        return this.booleanExpression;
    }

    /**
     * Get the list of the statements
     * @return the list of the statements;
     */
    public CompositeStatement getWhileStatement() {
        return this.whileStatement;
    }
}
