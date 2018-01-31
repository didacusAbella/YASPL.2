package com.didacusabella.yaspl.semantic;

public interface Scopeable {

    void attachScope(Scope table);
    Scope getAttachedScope();
}
