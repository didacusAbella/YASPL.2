package com.didacusabella.yaspl.syntax;

import com.didacusabella.utils.EnvironmentGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FunctionCallTest {

    private Program program;

    @Before
    public void setUp() throws Exception {
        this.program = EnvironmentGenerator.setupEnvironment("/testFunctionCall.yaspl");
    }

    @Test
    public void getDomain() {
        FunctionCall fc = (FunctionCall) this.program.getStatements().get(0);
        assertEquals("intXint", fc.getDomain());
    }

    @Test
    public void getCodomain() {
        FunctionCall fc = (FunctionCall) this.program.getStatements().get(0);
        assertEquals("stringXstring", fc.getCodomain());
    }

    @Test
    public void emptyCall() throws Exception {
        this.program = EnvironmentGenerator.setupEnvironment("/testEmptyFunction.yaspl");
        FunctionCall fc = (FunctionCall) this.program.getStatements().get(1);
        assertEquals("int", fc.getCodomain());
        assertEquals("void", fc.getDomain());
    }
}