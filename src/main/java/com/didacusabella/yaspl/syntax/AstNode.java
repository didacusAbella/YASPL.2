package com.didacusabella.yaspl.syntax;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This class is a representation of YASPL language node used for create the
 * AST. is a visitable node into the scheme of the Visitor Pattern
 *
 * @version 1.0
 * @author didacusAbella
 * @since 1.0
 */
public abstract class AstNode {

  private final Location leftLocation;
  private final Location rightLocation;

  /**
   * Create a new generic yaspl node.
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   */
  public AstNode(Location leftLocation, Location rightLocation) {
    this.leftLocation = leftLocation;
    this.rightLocation = rightLocation;
  }

  /**
   * get the left location of this node
   *
   * @return the left location
   */
  public Location getLeftLocation() {
    return leftLocation;
  }

  /**
   * get the right location of this node
   *
   * @return the right location
   */
  public Location getRightLocation() {
    return rightLocation;
  }

  /**
   * Accept method used to implement visitor pattern
   *
   * @param visitor the visitor to accept
   * @param param additional parameter to pass into visitor. Use Void to pass
   * anything
   * @param <T> result type of this operation
   * @param <P> additional parameter
   * @return a result based of parameter T and P
   */
  public abstract <T, P> T accept(Visitor<T, P> visitor, P param);
}
