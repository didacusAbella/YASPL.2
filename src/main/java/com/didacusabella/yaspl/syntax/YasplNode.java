package com.didacusabella.yaspl.syntax;


import com.didacusabella.yaspl.visitor.Visitor;

/**
 * This class is a representation of YASPL language node used for create the AST. is a visitable node
 * into the scheme of the Visitor Pattern
 * @version 1.0
 * @author didacusAbella
 * @since 1.0
 */
public interface YasplNode {

    String getKind();
    void accept(Visitor visitor);

}
