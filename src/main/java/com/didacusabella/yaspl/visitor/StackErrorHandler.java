package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.AstNode;
import java.util.Stack;

/**
 *
 * @author didacus
 */
public class StackErrorHandler implements ErrorHandler {
  
  private final Stack<String> errors;

  public StackErrorHandler(Stack<String> errors) {
    this.errors = errors;
  }

  @Override
  public void reportError(String msg, AstNode node) {
    String message = String.format("%s at:%s/%s", msg, node.getLeftLocation(), node.getRightLocation());
    this.errors.push(message);
  }

  @Override
  public void logErrors() {
    this.errors.forEach(System.out::println);
  }
  
}
