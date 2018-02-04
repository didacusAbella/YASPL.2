package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * This node wrapping the primitive typeof a YASPL program.
 * @since 1.0
 * @author didacusAbella
 */
public class Type extends YasplNode {

    private final String typeName;

    /**
     * Create a new Type node
     * @param leftLocation the left location
     * @param rightLocation the right location
     * @param typeName the type name
     */
    public Type(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation,
                String typeName) {
        super(leftLocation, rightLocation);
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
