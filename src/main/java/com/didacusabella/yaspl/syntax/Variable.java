package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node encapsulate a variable of a YASPL program. For example:
 * <pre>
 *     {@code
 *      a;
 *     }
 * </pre>
 */
public class Variable extends YasplNode {

    private final Identifier identifier;

    /**
     * Create a new variable
     * @param identifier the name of the variable
     */
    public Variable(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    /**
     * Get the name of the variable
     * @return the name of the variable
     */
    public Identifier getIdentifier() {
        return this.identifier;
    }


}
