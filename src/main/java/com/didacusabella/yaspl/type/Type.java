package com.didacusabella.yaspl.type;


/**
 * This is a singleton base class
 * @author didacus
 */
public interface Type {
  
  PrimitiveType checkAdd(PrimitiveType type);
  PrimitiveType checkSub(PrimitiveType type);
  PrimitiveType checkMul(PrimitiveType type);
  PrimitiveType checkDiv(PrimitiveType type);
  PrimitiveType checkRel(PrimitiveType type);
  
}
