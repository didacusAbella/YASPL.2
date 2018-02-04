package com.didacusabella.yaspl.lexical;

import java.util.Objects;

/**
 * A lexical symbol is used to store data into the string table for compacting the memory usage
 */
public class LexicalSymbol {

    private final String lexeme;
    private final int code;

    /**
     * Create a new lexical symbol
     * @param lexeme the string name
     * @param code the code
     */
    public LexicalSymbol(String lexeme, int code) {
        this.lexeme = lexeme;
        this.code = code;
    }

    /**
     * Get the lexeme of this symbol
     * @return the lexeme
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * Get the code of this symbol
     * @return the code
     */
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
