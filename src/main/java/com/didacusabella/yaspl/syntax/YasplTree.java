package com.didacusabella.yaspl.syntax;


import com.didacusabella.yaspl.visitor.Visitor;
import com.googlecode.jctree.ArrayListTree;
import com.googlecode.jctree.NodeNotFoundException;
import java_cup.runtime.ComplexSymbolFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a representation of YASPL language subtree used for create the AST. is a visitable node
 * into the scheme of the Visitor Pattern
 * @version 1.0
 * @author didacusAbella
 * @since 1.0
 */
public abstract class YasplTree extends ArrayListTree<YasplTree> {

     public YasplTree(){
          super();
     }

     public YasplTree(ComplexSymbolFactory.ComplexSymbol symbol){
          super();
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

     protected final <E> List<E> subTrees(Class<E> klass){
          try {
               return this.children(this).stream().filter(klass::isInstance)
                       .map(klass::cast)
                       .collect(Collectors.toList());
          } catch (NodeNotFoundException e) {
               e.printStackTrace();
          }
          return null;
     }

     protected final <E> E subTree(Class<E> klass){
          try {
               return this.children(this).stream().filter(klass::isInstance)
                       .findFirst().map(klass::cast).get();
          } catch (NodeNotFoundException e) {
               e.printStackTrace();
          }
          return null;
     }

     public List<YasplTree> ownChildren(){
          try {
               return this.children(this);
          } catch (NodeNotFoundException e) {
               e.printStackTrace();
          }
          return null;
     }
}
