package com.didacusabella.yaspl.type;

/**
 *
 * @author didacus
 */
public enum PrimitiveType implements Type {

  BOOL {
    @Override
    public PrimitiveType checkAdd(PrimitiveType type) {
      switch(type){
        case BOOL:
          return INT;
        case DOUBLE:
          return DOUBLE;
        case INT:
          return INT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkSub(PrimitiveType type) {
      switch(type){
        case BOOL:
          return INT;
        case DOUBLE:
          return DOUBLE;
        case INT:
          return INT;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkMul(PrimitiveType type) {
      switch(type){
        case BOOL: case INT:
          return INT;
        case DOUBLE:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkDiv(PrimitiveType type) {
      switch(type){
        case BOOL: case INT:
          return INT;
        case DOUBLE:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkRel(PrimitiveType type) {
      switch(type){
        case INT: case DOUBLE: case BOOL:
          return BOOL;
        default:
          return NULL;
      }
    }
  },
  
  DOUBLE {
    @Override
    public PrimitiveType checkAdd(PrimitiveType type) {
      switch(type){
        case INT: case DOUBLE: case BOOL:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkSub(PrimitiveType type) {
      switch(type){
        case INT: case DOUBLE: case BOOL:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkMul(PrimitiveType type) {
      switch(type){
        case INT: case DOUBLE: case BOOL:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkDiv(PrimitiveType type) {
      switch(type) {
        case INT: case DOUBLE: case BOOL:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkRel(PrimitiveType type) {
      switch(type){
        case INT: case BOOL: case DOUBLE:
          return BOOL;
        default:
          return NULL;
      }
    }
    
  },
  INT {
    @Override
    public PrimitiveType checkAdd(PrimitiveType type) {
      switch(type) {
        case INT: case BOOL:
          return INT;
        case DOUBLE:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkSub(PrimitiveType type) {
      switch(type) {
        case INT: case BOOL:
          return INT;
        case DOUBLE:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkMul(PrimitiveType type) {
      switch(type){
        case INT: case BOOL:
          return INT;
        case DOUBLE:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkDiv(PrimitiveType type) {
      switch(type){
        case INT: case BOOL:
          return INT;
        case DOUBLE:
          return DOUBLE;
        default:
          return NULL;
      }
    }

    @Override
    public PrimitiveType checkRel(PrimitiveType type) {
      switch(type) {
        case INT: case DOUBLE: case BOOL:
          return BOOL;
        default:
          return NULL;
      }
    }
    
  },
  NULL {
    @Override
    public PrimitiveType checkAdd(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkSub(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkMul(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkDiv(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkRel(PrimitiveType type) {
      return NULL;
    }
    
  },
  STRING {
    @Override
    public PrimitiveType checkAdd(PrimitiveType type) {
      switch(type){
        case NULL:
          return NULL;
        default:
          return STRING;
      }
    }

    @Override
    public PrimitiveType checkSub(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkMul(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkDiv(PrimitiveType type) {
      return NULL;
    }

    @Override
    public PrimitiveType checkRel(PrimitiveType type) {
      return NULL;
    }
  };

  @Override
  public String toString() {
    switch(this){
      case BOOL:
        return "bool";
      case INT:
        return "int";
      case DOUBLE:
        return "double";
      case STRING:
        return "string";
      default:
        return "undefined";
    }
  }
  
  
}
