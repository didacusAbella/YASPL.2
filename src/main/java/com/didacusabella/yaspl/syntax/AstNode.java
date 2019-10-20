package com.didacusabella.yaspl.syntax;
import com.didacusabella.yaspl.visitor.Visitable;
import java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * This class is the base class for all other nodes of the AST. It contains 
 * the location of node creation in a YASPL file based on CUP's Location class and implements
 * the Visitable interface for implementing the Visitor Pattern
 */
public abstract class AstNode implements Visitable {

  private final Location leftLocation;
  private final Location rightLocation;

  /**
   * Create a new generic AST node.
   *
   * @param leftLocation the left location
   * @param rightLocation the right location
   */
  public AstNode(Location leftLocation, Location rightLocation) {
    this.leftLocation = leftLocation;
    this.rightLocation = rightLocation;
  }

  /**
   * 
   * @return The left position 
   */
  public Location getLeftLocation() {
    return leftLocation;
  }

  /**
   * 
   * @return The right position
   */
  public Location getRightLocation() {
    return rightLocation;
  }
}
