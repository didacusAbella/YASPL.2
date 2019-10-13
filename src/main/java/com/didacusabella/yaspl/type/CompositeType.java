package com.didacusabella.yaspl.type;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author didacus
 */
public class CompositeType extends Type {
  
  private final List<Type> types;

  public CompositeType(List<Type> types) {
    this.types = types;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 97 * hash + Objects.hashCode(this.types);
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
    } else{
      final CompositeType other = (CompositeType) obj;
      return this.types.equals(other);
    }
  }

}
