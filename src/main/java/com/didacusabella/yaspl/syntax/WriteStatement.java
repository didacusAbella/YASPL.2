package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.List;

/**
 * This the node for a Write statement in a YASPL program. For example:
 * <pre>
 *     {@code
 *     "Hello" -> ;
 *     }
 * </pre>
 */
public class WriteStatement extends Statement {


    private final List<Expression> expressions;

    public WriteStatement(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                          List<Expression> expressions) {
        super(leftLocation, rightLocation);
        this.expressions = expressions;
    }

    /**
     * Get the list of the expression to write
     * @return the list of expression
     */
    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    @Override
    public boolean checkType() {
       return checkAll(expressions);
    }
}
