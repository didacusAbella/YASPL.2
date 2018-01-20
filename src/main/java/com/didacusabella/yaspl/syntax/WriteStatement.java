package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

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

    /**
     * Create a new write statement node
     * @param expressions the expression to write
     */
    public WriteStatement(List<Expression> expressions) {
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
}
