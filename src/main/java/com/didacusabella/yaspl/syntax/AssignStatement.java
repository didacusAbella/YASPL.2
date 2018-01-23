package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * A Tree node for the an assignment. For example:
 * <pre>
 *     {@code
 *     x = 1;
 *     }
 * </pre>
 * @since 1.0
 * @author didacusAbella
 */
public class AssignStatement extends Statement {

    private final Identifier identifier;
    private final Expression expression;

    public AssignStatement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                           Identifier identifier, Expression expression) {
        super(leftLocation, rightLocation);
        this.identifier = identifier;
        this.expression = expression;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
