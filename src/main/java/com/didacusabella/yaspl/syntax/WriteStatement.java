package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This the node for a Write statement in a YASPL program. For example:
 * <pre>
 *     {@code
 *     "Hello" -> ;
 *     }
 * </pre>
 */
public class WriteStatement extends Statement {

  private final List<Expression> expressions;

  /**
   * Create a new write expression node
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   * @param expressions the expression node list
   */
  public WriteStatement(Location leftLocation, Location rightLocation,
          List<Expression> expressions) {
    super(leftLocation, rightLocation);
    this.expressions = expressions;
  }

  /**
   * Get the list of the expression to write
   *
   * @return the list of expression
   */
  public List<Expression> getExpressions() {
    return expressions;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
