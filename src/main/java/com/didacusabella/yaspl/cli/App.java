package com.didacusabella.yaspl.cli;
import java.io.*;
import java.util.Scanner;

/**
 * Main Class
 */
public class App {

  private static final String START_MESSAGE = "File not passed. Chose what file compile.\n"
          + "1)sum.yaspl\n2)product.yaspl\n3)division.yaspl\n4)pow.yaspl\n5)fibonacci.yaspl";
  private static Compiler compiler;

  /**
   * Main method for exec class
   *
   * @param args the srguments
   * @throws java.io.FileNotFoundException if file not found
   */
  public static void main(String[] args) throws FileNotFoundException, Exception {
    if(args.length == 0){
      System.out.println(START_MESSAGE);
      Scanner testNumber = new Scanner(System.in);
      String testFile = chooseTestFile(testNumber.nextInt());
      String[] testOptions = { testFile, "-xml" };
      compiler = new Compiler(App.class.getResourceAsStream(testFile), testOptions);
      compiler.compileFile();
      System.exit(0);
    } else {
      String pathName = new File(args[0]).getAbsolutePath();
      compiler = new Compiler(new FileInputStream(pathName), args);
      compiler.compileFile();
      System.exit(0);
    }
  }

  private static String chooseTestFile(int number) {
    switch (number) {
      case 1:
        return "sum.yaspl";
      case 2:
        return "product.yaspl";
      case 3:
        return "division.yaspl";
      case 4:
        return "pow.yaspl";
      case 5:
        return "fibonacci.yaspl";
      default:
        return "test.yaspl";
    }
  }
}
