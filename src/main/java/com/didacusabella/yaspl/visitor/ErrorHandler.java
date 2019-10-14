package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.AstNode;

/**
 *
 * @author didacus
 */
public interface ErrorHandler {
  
  void reportError(String msg, AstNode node);
  
  void logErrors();
}
