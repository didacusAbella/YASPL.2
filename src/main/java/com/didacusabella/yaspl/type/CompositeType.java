package com.didacusabella.yaspl.type;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author didacus
 */
public class CompositeType implements Type {
  
  private final List<Type> types;

  public CompositeType(List<Type> types) {
    this.types = types;
  }

  @Override
  public PrimitiveType checkAdd(PrimitiveType type) {
    return PrimitiveType.NULL;
  }

  @Override
  public PrimitiveType checkSub(PrimitiveType type) {
    return PrimitiveType.NULL;
  }

  @Override
  public PrimitiveType checkMul(PrimitiveType type) {
    return PrimitiveType.NULL;
  }

  @Override
  public PrimitiveType checkDiv(PrimitiveType type) {
    return PrimitiveType.NULL;
  }

  @Override
  public PrimitiveType checkRel(PrimitiveType type) {
    return PrimitiveType.NULL;
  }
  
  public void addType(Type type){
    this.types.add(type);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.types);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }else if (obj == null) {
      return false;
    } else if (getClass() != obj.getClass()) {
      return false;
    } else {
    final CompositeType other = (CompositeType) obj;
    return !Objects.equals(this.types, other.types); 
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    this.types.forEach(t -> sb.append(t.toString()));
    return sb.toString();
  }

  

}
