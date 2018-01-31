package com.didacusabella.yaspl.syntax;

import com.didacusabella.utils.EnvironmentGenerator;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class FunctionDeclarationTest {

    private Program program;

    @Before
    public void setUp() throws Exception {
        this.program = EnvironmentGenerator.setupEnvironment("/testFunctionDeclaration.yaspl");
    }

    @Test
    public void domainString() throws Exception {
        FunctionDeclaration fd = (FunctionDeclaration) program.getDeclarations().get(0);
        assertEquals("intXintXint", fd.domainString());
    }

    @Test
    public void codomainString() throws Exception {
        FunctionDeclaration fd = (FunctionDeclaration) program.getDeclarations().get(0);
        assertEquals("intXintXint", fd.codomainString());
    }

    @Test
    public void emptyDomain() throws Exception{
        this.program = EnvironmentGenerator.setupEnvironment("/testEmptyFunction.yaspl");
        FunctionDeclaration fd = (FunctionDeclaration) program.getDeclarations().get(0);
        assertEquals("void", fd.domainString());
        assertEquals("int", fd.codomainString());
    }
}