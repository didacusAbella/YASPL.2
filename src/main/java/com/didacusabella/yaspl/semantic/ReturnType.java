package com.didacusabella.yaspl.semantic;

/**
 * Used for categorize node return type in semantic analysis
 */
public enum ReturnType {

    UNDEFINED("undefined"), VOID("void"), INTEGER("int"), DOUBLE("double"), BOOLEAN("bool"), STRING("string");

    private String value;

    ReturnType(String value) {
        this.value = value;
    }

    /**
     * Get the string value for this enum
     * @return the string value of this enum
     */
    public String getValue() {
        return value;
    }

    /**
     * Set a new value for this enum
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the enum for a specfic string value
     * @param value the string to search
     * @return the return type associated
     */
    public static ReturnType getEnumFor(String value){
        for(ReturnType t : values()){
            if(t.getValue().equals(value)){
                return t;
            }
        }
        return ReturnType.UNDEFINED;
    }
}
