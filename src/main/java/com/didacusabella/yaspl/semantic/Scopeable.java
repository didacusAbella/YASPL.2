package com.didacusabella.yaspl.semantic;

/**
 * This interface is used to attach the scope on some particular object. Nodes in YASPL
 */
public interface Scopeable {
    /**
     * Attach a scope table to an object
     * @param table the scope to attach
     */
    void attachScope(Scope table);

    /**
     * Retrieve the table attached
     * @return the scope
     */
    Scope getAttachedScope();
}
