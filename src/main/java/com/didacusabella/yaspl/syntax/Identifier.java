package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * This class wrap the identifiers. For example
 * <pre>
 *     {@code
 *     int id;
 *     }
 * </pre>
 */
public class Identifier extends Expression {

    private final String name;

    public Identifier(ComplexSymbolFactory.Location leftLocation,
                      ComplexSymbolFactory.Location rightLocation, String name) {
        super(leftLocation, rightLocation);
        this.name = name;
    }

    /**
     * return the name of the identifier
     * @return themname of the identifier
     */
    public String getName(){
       return this.name;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

}
