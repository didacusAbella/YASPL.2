package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.YasplNode;

/**
 * The error generator class is used for maange error during semantic analysis
 */
public class ErrorGenerator {
    /**
     * Create error during yaspl node visiting
     * @param msg the message to generate
     * @param node the node visited
     * @return a string error
     */
    public static String generateError(String msg, YasplNode node){
        return String.format("%s at:%s/%s", msg, node.getLeftLocation(), node.getRightLocation());
    }

}
