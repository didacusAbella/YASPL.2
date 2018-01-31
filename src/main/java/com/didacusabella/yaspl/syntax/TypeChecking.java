package com.didacusabella.yaspl.syntax;


import com.didacusabella.yaspl.semantic.ReturnType;

import java.util.List;

public interface TypeChecking {

    boolean checkType();
    default boolean checkAll(List<? extends YasplNode> list){
        return list.stream().allMatch(node -> node.getNodeType() != ReturnType.UNDEFINED);
    }
}
