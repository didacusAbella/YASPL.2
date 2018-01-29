package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

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

    public IfThenElseStatement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                               BooleanExpression ifCondition, CompositeStatement thenStatement,
                               CompositeStatement elseStatement) {
        super(leftLocation, rightLocation);
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

    @Override
    public boolean checkType() {
        return ifCondition.checkType() && thenStatement.checkType() && elseStatement.checkType();
    }
}
