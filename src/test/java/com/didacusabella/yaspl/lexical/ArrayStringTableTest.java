/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.didacusabella.yaspl.lexical;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author didacus
 */
public class ArrayStringTableTest {
  
  private static StringTable table;
  
  public ArrayStringTableTest() {
    super();
  }
  
  @BeforeClass
  public static void setUpClass() {
    table = new ArrayStringTable();
    table.install("identifier");
  }

  @Test
  public void testInstallNoDuplicates() {
    assertTrue(table.install("another"));
  }
  
  @Test
  public void testInstallWithDuplicate() {
    assertFalse(table.install("identifier"));
  }

  @Test
  public void testGetAddress() {
    assertEquals(0, table.getAddress("identifier"));
  }

  @Test
  public void testGetLexeme() {
    assertEquals("identifier", table.getLexeme(0));
  }
  
}
