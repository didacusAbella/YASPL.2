package com.didacusabella.yaspl.cli;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import com.didacusabella.yaspl.lexical.ArrayStringTable;
import com.didacusabella.yaspl.lexical.StringTable;
import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.semantic.SemanticException;
import com.didacusabella.yaspl.semantic.StackSymbolTable;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.Program;
import com.didacusabella.yaspl.visitor.CodeVisitor;
import com.didacusabella.yaspl.visitor.SemanticVisitor;
import com.didacusabella.yaspl.visitor.SyntaxVisitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Main Class
 */
public class App 
{
    private static final String OUTPUT_PATH = System.getProperty("user.home");
    private static final String START_MESSAGE = "File not passed. Chose what file compile.\n" +
            "1)sum.yaspl\n2)product.yaspl\n3)division.yaspl\n4)pow.yaspl\n5)fibonacci.yaspl";
    private static StringBuilder pathBuilder = new StringBuilder();
    private static ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
    private static StringTable stringTable = new ArrayStringTable();
    private static final String EMCC_PATH = System.getProperty("user.home") + "/ExternalTools/emsdk-portable/emscripten/1.37.33/emcc";

    /**
     * Main method for exec class
     * @param args the srguments
     * @throws Exception if some error occurred during runtime
     */
    public static void main( String[] args ) throws Exception {
        String testFile = "";
        if(args.length == 0){
            System.out.println(START_MESSAGE);
            Scanner input = new Scanner(System.in);
            switch(input.nextInt()){
                case 1:
                    testFile = "sum.yaspl";
                    break;
                case 2:
                    testFile = "product.yaspl";
                    break;
                case 3:
                    testFile = "division.yaspl";
                    break;
                case 4:
                    testFile = "pow.yaspl";
                    break;
                case 5:
                    testFile = "fibonacci.yaspl";
                    break;
                    default:
                        System.out.println("Command not valid");
                        System.exit(1);
                        break;

            }
            compileFile(testFile, args);
            System.out.println("Compiling success");
            System.exit(0);
        }else  {
            testFile = args[0];
            compileFile(testFile, args);
            System.out.println("Compiling success");
            System.exit(0);
        }
    }

    /**
     * Compile the file
     * @param testFile the file name to compile
     * @param options various options to include during compilation
     * @throws Exception if some error occurred during compiling
     * <pre>
     *    {@code
     *    java -jar yaspl2cc.jar filename -xml //generate xml output during compilation
     *    java -jar yaspl2cc.jar filename -web //generate js output
     *    }
     * </pre>
     */
    private static void compileFile(String testFile, String[] options) throws Exception {
        InputStream is = (options.length == 0) ? App.class.getResourceAsStream(testFile) : new FileInputStream(new File(testFile));
        /********************Lexical and syntax analysis******************************/
        Lexer lexer = new Lexer(complexSymbolFactory, is, stringTable);
        Parser parser = new Parser(lexer, complexSymbolFactory);
        Program program = (Program) parser.parse().value;
        /**********************Semantic Analisys*************************************/
        SymbolTable symbolTable = new StackSymbolTable(stringTable);
        SemanticVisitor semanticVisitor = new SemanticVisitor(symbolTable);
        semanticVisitor.visit(program, Logger.getLogger(App.class.getSimpleName()));
        if(program.getNodeType() == ReturnType.UNDEFINED) {
            throw new SemanticException("Semantic Error");
        }
        /*************************Code Generation************************************/
        CodeVisitor cVisitor = new CodeVisitor(symbolTable);
        String generatedOutput = testFile.replace("yaspl", "c");
        pathBuilder.append(OUTPUT_PATH).append('/').append(generatedOutput);
        File generatedFile = new File(pathBuilder.toString());
        FileWriter pw = new FileWriter(generatedFile);
        pw.write(cVisitor.visit(program, null));
        pw.close();
        pathBuilder.setLength(0);
        pathBuilder.append(OUTPUT_PATH).append('/').append(testFile.replace(".yaspl", ""));
        Process p = Runtime.getRuntime().exec(String.format("clang-5.0 -std=c99 %s -o %s", generatedFile.getAbsolutePath(),
                pathBuilder.toString()));
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        handleIO(stdInput, true);
        handleIO(stdError, false);
        /************************XML generation option*******************************/
        if(hasOption(options, "-xml")){
            pathBuilder.setLength(0);
            pathBuilder.append(testFile.replace("yaspl", "xml"));
            SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
            syntaxVisitor.appendRoot(syntaxVisitor.visit(program, null));
            syntaxVisitor.toXml(pathBuilder.toString());
        }
        /*************************JS generation Option****************************/
        if(hasOption(options, "-web")){
            pathBuilder.setLength(0);
            pathBuilder.append(OUTPUT_PATH).append('/').append(testFile.replace("yaspl", "js"));
            p = Runtime.getRuntime().exec(String.format("%s -std=c99 %s -o %s ", EMCC_PATH, generatedFile.getAbsolutePath(),
                    pathBuilder.toString()));
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            handleIO(stdInput, true);
            handleIO(stdError, false);
        }
    }

    /**
     * Handle io buffering for process exec management
     * @param bufferedReader the buffered reader to fill
     * @param out boolean that tell where to redirect buffer
     * @throws IOException if some error happen during buffering
     */
    private static void handleIO(BufferedReader bufferedReader, boolean out) throws IOException {
        String messages;
        while ((messages = bufferedReader.readLine()) != null){
            if(out)
                System.out.println(messages);
            else
                System.err.println(messages);
        }
    }

    /**
     * This method check whenever there are some particular option during compiling
     * @param options the options passed
     * @param command the searched option
     * @return true if the option is contained. False otherwise
     */
    private static boolean hasOption(String[] options, String command){
        for(String i : options)
            if(i.equals(command))
                return true;
        return false;
    }
}
