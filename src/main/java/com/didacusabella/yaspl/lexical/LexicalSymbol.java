package com.didacusabella.yaspl.lexical;

import java.util.Objects;

public class LexicalSymbol {

    private final String lexeme;
    private final int code;

    public LexicalSymbol(String lexeme, int code) {
        this.lexeme = lexeme;
        this.code = code;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LexicalSymbol that = (LexicalSymbol) o;
        return code == that.code &&
                Objects.equals(lexeme, that.lexeme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexeme, code);
    }

    @Override
    public String toString() {
        return "LexicalSymbol{" +
                "lexeme='" + lexeme + '\'' +
                ", code=" + code +
                '}';
    }
}
