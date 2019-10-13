package com.didacusabella.yaspl.type;

/**
 *
 * @author didacus
 */
public abstract class Type {

  @Override
  public boolean equals(Object obj) {
    return this.getClass() == obj.getClass();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash;
  }
  
}
