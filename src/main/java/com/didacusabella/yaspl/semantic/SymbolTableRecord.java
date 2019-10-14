package com.didacusabella.yaspl.semantic;

import com.didacusabella.yaspl.type.Type;

/**
 *
 * @author didacus
 */
public class SymbolTableRecord {
  
  private Type type;
  private SymbolKind kind;

  public SymbolTableRecord(Type type, SymbolKind kind) {
    this.type = type;
    this.kind = kind;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public SymbolKind getKind() {
    return kind;
  }

  public void setKind(SymbolKind kind) {
    this.kind = kind;
  }
}
