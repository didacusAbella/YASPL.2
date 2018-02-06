package com.didacusabella.yaspl.visitor;

import com.didacusabella.utils.EnvironmentGenerator;
import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.syntax.*;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class SemanticVisitorTest {

    private Program program;
    private SemanticVisitor semanticVisitor;

    @Before
    public void setUp() throws Exception {
        this.program = EnvironmentGenerator.setupEnvironment("testSemanticVisitor.yaspl");
    }

    @Test
    public void testProgramType() {
        assertEquals(ReturnType.VOID, program.getNodeType());
    }

    @Test
    public void testProgram() {
        assertTrue(program.getDeclarations().stream().allMatch(d -> d.getNodeType() != ReturnType.UNDEFINED));
        assertTrue(program.getStatements().stream().allMatch(s -> s.getNodeType() != ReturnType.UNDEFINED));
    }

    @Test
    public void testVariableDeclaration() {
        assertTrue(program.getDeclarations().get(0).getNodeType() == ReturnType.INTEGER);
        assertTrue(program.getDeclarations().get(1).getNodeType() == ReturnType.DOUBLE);
        assertTrue(program.getDeclarations().get(2).getNodeType() == ReturnType.BOOLEAN);
        assertTrue(program.getDeclarations().get(3).getNodeType() == ReturnType.STRING);
        assertTrue(program.getDeclarations().get(4).getNodeType() == ReturnType.VOID);
    }

    @Test
    public void testFunctionInput() {
        FunctionDeclaration fd = (FunctionDeclaration) this.program.getDeclarations().get(4);
        assertTrue(fd.getVariableDeclarations().stream().allMatch(vd -> vd.getNodeType() != ReturnType.UNDEFINED));
        assertTrue(fd.getVariableDeclarations().get(0).getNodeType() == ReturnType.INTEGER);
        assertTrue(fd.getVariableDeclarations().get(1).getNodeType() == ReturnType.DOUBLE);
    }

    @Test
    public void testFunctionOutput() {
        FunctionDeclaration fd = (FunctionDeclaration) this.program.getDeclarations().get(4);
        assertTrue(fd.getParameterDeclarations().get(0).getVariableDeclarationList().get(0).getNodeType() == ReturnType.BOOLEAN);
        assertTrue(fd.getParameterDeclarations().get(1).getVariableDeclarationList().get(0).getNodeType() == ReturnType.STRING);
    }

    @Test
    public void testBody() {
        FunctionDeclaration fd = (FunctionDeclaration) this.program.getDeclarations().get(4);
        assertEquals(ReturnType.VOID, fd.getBody().getNodeType());
    }

    @Test
    public void testReadStatement() {
        ReadStatement rs = (ReadStatement) program.getStatements().get(0);
        assertEquals(ReturnType.VOID, rs.getNodeType());
        assertEquals("intXint", rs.variableDomain());
        assertEquals("intXint", rs.typeDomain());
    }

    @Test
    public void testAssignStatementwithConst() {
        AssignStatement as = (AssignStatement) program.getStatements().get(1);
        assertEquals(ReturnType.VOID, as.getNodeType());
    }

    @Test
    public void testAssignStatementWithOtherVar() {
        AssignStatement as = (AssignStatement) program.getStatements().get(2);
        assertEquals(ReturnType.VOID, as.getNodeType());
    }

    @Test
    public void testIfThenStatement() {
        IfThenStatement it = (IfThenStatement) program.getStatements().get(3);
        assertEquals(ReturnType.BOOLEAN, it.getIfCondition().getNodeType());
        assertEquals(ReturnType.VOID, it.getThenStatement().getNodeType());
    }

    @Test
    public void testIfThenElseStatement() {
        IfThenElseStatement ite = (IfThenElseStatement) program.getStatements().get(4);
        assertEquals(ReturnType.BOOLEAN, ite.getIfCondition().getNodeType());
        assertEquals(ReturnType.VOID, ite.getThenStatement().getNodeType());
        assertEquals(ReturnType.VOID, ite.getElseStatement().getNodeType());
    }

    @Test
    public void testWhileStatements() {
        WhileStatement ws = (WhileStatement) program.getStatements().get(5);
        assertEquals(ReturnType.BOOLEAN, ws.getWhileCondition().getNodeType());
        assertEquals(ReturnType.VOID, ws.getWhileStatement().getNodeType());
    }

    @Test
    public void testZFunctionCall() {
        FunctionCall fc = (FunctionCall) program.getStatements().get(6);
        assertEquals("intXintXdoubleXdouble", fc.getDomain());
        assertEquals("boolXboolXstringXstring", fc.getCodomain());
        assertTrue(fc.getExpressions().stream().allMatch(e -> e.getNodeType() != ReturnType.UNDEFINED));
        assertTrue(fc.getVariableList().stream().allMatch(v -> v.getNodeType() != ReturnType.UNDEFINED));
    }

}