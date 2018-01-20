package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This node wrapping the primitive typeof a YASPL program.
 * @since 1.0
 * @author didacusAbella
 */
public class Type extends YasplNode {

    private final String typeName;

    /**
     * Create a new primitive type
     * @param typeName the type associated
     */
    public Type(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Get the string representation of primitive type
     * @return the primitive type
     */
    public String getTypeName() {
        return typeName;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }
}
