package com.didacusabella.yaspl.visitor;

/**
 *
 * @author didacus
 */
public interface Visitable {
  
  /**
   * Accept the visitor
   * @param <T> return type
   * @param <P> type od the parameter
   * @param visitor the visitor 
   * @param arg the additional argument
   * @return the returntype defined by visitor
   */
  <T, P> T accept(Visitor<T, P> visitor, P arg);
}
