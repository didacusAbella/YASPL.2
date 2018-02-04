package com.didacusabella.yaspl.semantic;

/**
 * A semantic exception occurs when a node is undefined
 */
public class SemanticException extends Exception{

    public SemanticException(String message) {
        super(message);
    }
}
