package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.YasplNode;

/**
 * Abstract visitor
 */
public interface Visitor {
    void visit(YasplNode node);
}