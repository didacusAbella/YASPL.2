package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * Create an if then else node statement. For example:
 * <pre>
 *     {@code
 *     if(booleanExpression) then {
 *         statements;
 *     }else {
 *         statements
 *     };
 *     }
 * </pre>
 */
public class IfThenElseStatement extends Statement {

    private final BooleanExpression ifCondition;
    private final CompositeStatement thenStatement;
    private final CompositeStatement elseStatement;

    /**
     * Create a new if then else statement
     * @param ifCondition the if condition
     * @param thenStatement the statement's list of then body
     * @param elseStatement the statements's list of else body
     */
    public IfThenElseStatement(BooleanExpression ifCondition,
                               CompositeStatement thenStatement, CompositeStatement elseStatement) {
        this.ifCondition = ifCondition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    /**
     * get the if condition
     * @return the if condition
     */
    public BooleanExpression getIfCondition() {
        return this.ifCondition;
    }

    /**
     * get the then body
     * @return the then body
     */
    public Statement getThenStatement() {
        return this.thenStatement;
    }

    /**
     * get the else body
     * @return the else body
     */
    public Statement getElseStatement() {
        return this.elseStatement;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
