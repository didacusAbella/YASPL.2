package com.didacusabella.yaspl.syntax;


import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * This class is a representation of YASPL language node used for create the AST. is a visitable node
 * into the scheme of the Visitor Pattern
 * @version 1.0
 * @author didacusAbella
 * @since 1.0
 */
public abstract class YasplNode {

     private final ComplexSymbolFactory.Location leftLocation;
     private final ComplexSymbolFactory.Location rightLocation;
     private ReturnType nodeType;

     public YasplNode(ComplexSymbolFactory.Location leftLocation, ComplexSymbolFactory.Location rightLocation) {
          this.leftLocation = leftLocation;
          this.rightLocation = rightLocation;
          this.nodeType = ReturnType.UNDEFINED;
     }

     public ComplexSymbolFactory.Location getLeftLocation() {
          return leftLocation;
     }

     public ComplexSymbolFactory.Location getRightLocation() {
          return rightLocation;
     }

     /**
      * Accept method used to implement visitor pattern
      * @param visitor the visitor to accept
      * @param param additional parameter to pass into visitor. Use Void to pass anything
      * @param <T> result type of this operation
      * @param <P> additional parameter
      * @return a result based of parameter T and P
      */
     public abstract <T, P> T accept(Visitor<T,P> visitor, P param);

    public ReturnType getNodeType() {
        return nodeType;
    }

    public void setNodeType(ReturnType nodeType) {
        this.nodeType = nodeType;
    }
}
