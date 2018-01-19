package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This is the Tree node for a BinaryExpression. Is used for handle math operations. For example:
 * <pre>
 *     {@code
 *     2+2;
 *     }
 * </pre>
 * @since 1.0
 * @author didacusAbella
 */
public class BinaryExpression extends Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;
    private final String op;

    /**
     * Create a new Binary Expression
     * @param leftOperand the left operand of the expression
     * @param rightOperand the right operand of the expression
     * @param op the operation associated with the expression
     */
    public BinaryExpression(Expression leftOperand, Expression rightOperand, String op) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.op = op;
    }

    /**
     * Return the operation associated to this expression
     * @return the operation
     */
    public String getOp() {
        return op;
    }

    /**
     * Return the left operand of the expression
     * @return the left operand
     */
    public Expression getLeftOperand() {
        return leftOperand;
    }

    /**
     * Return the right operand of the expression
     * @return the right operand
     */
    public Expression getRightOperand() {
        return rightOperand;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
