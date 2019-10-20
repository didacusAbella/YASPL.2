package com.didacusabella.yaspl.syntax;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This the node for a Write statement in a YASPL program. For example:
 * {@code "Hello" -> ; }
 */
public class WriteStatement extends Statement {

  private final List<Expression> expressions;

  /**
   * {@inheritDoc}
   * @param expressions the expression node list
   */
  public WriteStatement(Location leftLocation, Location rightLocation,
          List<Expression> expressions) {
    super(leftLocation, rightLocation);
    this.expressions = expressions;
  }

  public List<Expression> getExpressions() {
    return expressions;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P arg) {
    return visitor.visit(this, arg);
  }

}
