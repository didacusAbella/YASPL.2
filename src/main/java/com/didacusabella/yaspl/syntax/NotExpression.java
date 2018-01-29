package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

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

    public NotExpression(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                         Expression expression) {
        super(leftLocation, rightLocation);
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

    @Override
    public boolean checkType() {
        return expression.getNodeType() == ReturnType.BOOLEAN;
    }
}
