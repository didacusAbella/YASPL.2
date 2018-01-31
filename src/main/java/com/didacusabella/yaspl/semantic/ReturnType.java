package com.didacusabella.yaspl.semantic;

public enum ReturnType {

    UNDEFINED("undefined"), VOID("void"), INTEGER("int"), DOUBLE("double"), BOOLEAN("bool"), STRING("string");

    private String value;

    ReturnType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ReturnType getEnumFor(String value){
        for(ReturnType t : values()){
            if(t.getValue().equals(value)){
                return t;
            }
        }
        return ReturnType.UNDEFINED;
    }
}
