package com.didacusabella.yaspl.error;

import com.didacusabella.yaspl.syntax.AstNode;
import com.didacusabella.yaspl.type.Type;

/**
 *
 * @author didacus
 */
public interface ErrorHandler {
  
  static final String TYPE_MISMATCH = "Type mismatch: Expected %s but found %s";
  static final String NOT_DEFINED = "Variable not defined";
  static final String YET_DEFINED = "Variable yet defined";
  
  /**
   * Report an error when visiting an AstNode
   * @param msg the error to create
   * @param node the node where the error occurred
   */
  void reportError(String msg, AstNode node);
  
  /**
   * Log the errors on standard output
   */
  void logErrors();
  
  default void reportTypeMismatch(Type expected, Type actual, AstNode node){
    String msg = String.format(TYPE_MISMATCH, expected, actual);
    this.reportError(msg, node);
  }
  
  default void reportNotDefined(AstNode node){
    this.reportError(NOT_DEFINED, node);
  }
  
  default void reportYetDefined(AstNode node){
    this.reportError(YET_DEFINED, node);
  }
}
