package com.didacusabella.yaspl.syntax;


import com.didacusabella.yaspl.visitor.Visitor;
import com.googlecode.jctree.ArrayListTree;
import com.googlecode.jctree.NodeNotFoundException;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a representation of YASPL language node used for create the AST. is a visitable node
 * into the scheme of the Visitor Pattern
 * @version 1.0
 * @author didacusAbella
 * @since 1.0
 */
public abstract class YasplNode {

     /**
      * Accept method used to implement visitor pattern
      * @param visitor the visitor to accept
      * @param param additional parameter to pass into visitor. Use Void to pass anything
      * @param <T> result type of this operation
      * @param <P> additional parameter
      * @return a result based of parameter T and P
      */
     public abstract <T, P> T accept(Visitor<T,P> visitor, P param);

}
