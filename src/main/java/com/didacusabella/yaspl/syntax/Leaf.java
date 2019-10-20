package com.didacusabella.yaspl.syntax;

/**
 * The leaf interface is used by leaves's nodes in the AST for retrieve the value associated
 * @param <T> return type of value
 */
public interface Leaf<T> {
  
  
  T getValue();
  
}
