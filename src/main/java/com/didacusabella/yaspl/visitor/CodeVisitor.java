package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.semantic.*;
import com.didacusabella.yaspl.syntax.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


public class CodeVisitor implements Visitor<String, Scope> {

    private static final String C_HEADER = "#include <stdio.h>\n" +
            "#include <stdbool.h>\n";

    private static final String COMMENT_HEADER = "/*This file was generated by yasplcc*/";
    private static final String DECL_HEADER = "/***********Declarations***********/";
    private static final String MAIN_HEADER = "/***********Main*******************/";
    private SymbolTable symbolTable;

    public CodeVisitor(SymbolTable table)  {
        super();
        this.symbolTable = table;
    }


    @Override
    public String visit(Program programNode, Scope param) {
        String declarations = this.compactCode(programNode.getDeclarations(), programNode.getAttachedScope());
        String statements = this.compactCode(programNode.getStatements(), programNode.getAttachedScope());
        StringBuilder programBuilder = new StringBuilder();
        programBuilder.append(COMMENT_HEADER).append('\n').append(C_HEADER).append('\n').append(DECL_HEADER)
                .append('\n').append(declarations).append('\n').append(MAIN_HEADER).append('\n')
                .append("int main(void){\n").append(statements).append('\n').append(" return 0;\n}\n");
        return programBuilder.toString();
    }

    @Override
    public String visit(VariableDeclaration variableDeclarationNode, Scope param) {
        StringBuilder builder = new StringBuilder();
        StringJoiner varJoiner = new StringJoiner(", ");
        String type = variableDeclarationNode.getType().accept(this, param);
        if(type.equals("string")){
            variableDeclarationNode.getVariables().forEach(v -> {
                varJoiner.add("* "+v.accept(this, param));
            });
            builder.append(toCType(type)).append(' ').append(varJoiner.toString()).append(';').append('\n');
        }else {
            variableDeclarationNode.getVariables().forEach(v -> {
                varJoiner.add(v.accept(this, param));
            });
            builder.append(type).append(' ').append(varJoiner.toString()).append(';').append('\n');
        }
        return builder.toString();
    }

    @Override
    public String visit(FunctionDeclaration functionDeclarationNode, Scope param) {
        StringBuilder functionBuilder = new StringBuilder();
        functionBuilder.append("void").append(' ').append(functionDeclarationNode.getIdentifier()
                .accept(this, param)).append('(');
        if(!functionDeclarationNode.getVariableDeclarations().isEmpty()){
            for(VariableDeclaration vd : functionDeclarationNode.getVariableDeclarations()){
                StringJoiner inputJoiner = new StringJoiner(", ");
                for (Variable v :vd.getVariables()){
                    int vAddress = this.symbolTable.findAddress(v.accept(this, functionDeclarationNode.getAttachedScope()));
                    String type = toCType(functionDeclarationNode.getAttachedScope().get(vAddress).getReturnType().getValue());
                    String name = this.symbolTable.getTable().getLexeme(vAddress);
                    inputJoiner.add(String.format("%s %s", type, name));
                }
                functionBuilder.append(inputJoiner.toString()).append(',');
            }
        }
        functionBuilder.append(compactCode(functionDeclarationNode.getParameterDeclarations(), functionDeclarationNode.getAttachedScope()));
        functionBuilder.append("){\n").append(functionDeclarationNode.getBody().accept(this, functionDeclarationNode.getAttachedScope())).append("\n}\n");
        return functionBuilder.toString();
    }

    @Override
    public String visit(Variable variableNode, Scope param) {
        return variableNode.getIdentifier().accept(this, param);
    }

    @Override
    public String visit(Type typeNode, Scope param) {
        return typeNode.getTypeName();
    }

    @Override
    public String visit(Identifier identifierNode, Scope param) {
        int address = this.symbolTable.findAddress(identifierNode.getName());
        SemanticSymbol ss = param.get(address);
        if(ss instanceof VariableSymbol){
            VariableSymbol vs = (VariableSymbol)ss;
            if(vs.getVarType() == VariableType.OUTPUT)
                return "*".concat(identifierNode.getName());
        }
        return identifierNode.getName();
    }

    @Override
    public String visit(ParameterDeclaration parameterDeclarationNode, Scope param) {
        StringJoiner outputJoiner = new StringJoiner(", ");
        parameterDeclarationNode.getVariableDeclarationList().forEach(vd -> {
            vd.getVariables().forEach(v -> {
                int oAddress = this.symbolTable.findAddress(v.getIdentifier().getName()); //need to stop for pointer issue
                String name = this.symbolTable.getTable().getLexeme(oAddress);
                String type = toCType(param.get(oAddress).getReturnType().getValue());
                outputJoiner.add(String.format("%s *%s", type, name));
            });
        });
        return outputJoiner.toString();
    }

    @Override
    public String visit(Body bodyNode, Scope param) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append(compactCode(bodyNode.getVariableDeclarationList(), param));
        bodyBuilder.append(compactCode(bodyNode.getStatements(), param));
        return bodyBuilder.toString();
    }

    @Override
    public String visit(ReadStatement readStatementNode, Scope param) {
        StringBuilder readBuilder = new StringBuilder();
        readBuilder.append("scanf(\"");
        StringJoiner type = new StringJoiner(" ");
        readStatementNode.getTypeList().forEach(t -> type.add(mapType(t.accept(this, param))));
        readBuilder.append(type.toString());
        readBuilder.append("\",");
        StringJoiner inputs = new StringJoiner(", ");
        readStatementNode.getIdentifierList().forEach(i -> inputs.add("&".concat(i.accept(this, param))));
        readBuilder.append(inputs.toString());
        readBuilder.append(");\n");
        return readBuilder.toString();
    }

    @Override
    public String visit(WriteStatement writeStatementNode, Scope param) {
        StringBuilder writerBuilder = new StringBuilder();
        StringJoiner output = new StringJoiner(", ");
        StringJoiner types = new StringJoiner(" ");
        writeStatementNode.getExpressions().forEach(e -> {
            types.add(mapType(e.getNodeType().getValue()));
            output.add(e.accept(this, param));
        });
        writerBuilder.append("printf(\"").append(types).append("\\n\", ").append(output).append(");\n");
        return writerBuilder.toString();
    }

    @Override
    public String visit(FunctionCall functionCallNode, Scope param) {
        StringBuilder functionBuilder = new StringBuilder();
        String functionName = functionCallNode.getFunctionName().accept(this, param);
        String input = "";
        if(!functionCallNode.getExpressions().isEmpty()){
            StringJoiner sj = new StringJoiner(", ");
            functionCallNode.getExpressions().forEach(e -> sj.add(e.accept(this, param)));
            input = sj.toString().concat(",");
        }
        StringJoiner output = new StringJoiner(", ");
        functionCallNode.getVariableList().forEach(v -> output.add("&".concat(v.accept(this, param))));
        functionBuilder.append(functionName).append('(').append(input).append(output).append(");");
        return functionBuilder.toString();
    }

    @Override
    public String visit(CompositeStatement compositeStatementNode, Scope param) {
        return compactCode(compositeStatementNode.getStatementList(), param);
    }

    @Override
    public String visit(WhileStatement whileStatementNode, Scope param) {
        StringBuilder whileBuilder = new StringBuilder();
        whileBuilder.append("while(");
        whileBuilder.append(whileStatementNode.getWhileCondition().accept(this, param));
        whileBuilder.append("){\n");
        whileBuilder.append(whileStatementNode.getWhileStatement().accept(this, param));
        whileBuilder.append("}\n");
        return whileBuilder.toString();
    }

    @Override
    public String visit(IfThenStatement ifThenStatementNode, Scope param) {
        StringBuilder ifBuilder = new StringBuilder();
        ifBuilder.append("if(").append(ifThenStatementNode.getIfCondition().accept(this, param)).append("){\n");
        ifBuilder.append(ifThenStatementNode.accept(this, param)).append("}\n");
        return ifBuilder.toString();
    }

    @Override
    public String visit(IfThenElseStatement ifThenElseStatementNode, Scope param) {
        StringBuilder ifBuilder = new StringBuilder();
        ifBuilder.append("if(").append(ifThenElseStatementNode.getIfCondition().accept(this, param)).append("){\n");
        ifBuilder.append(ifThenElseStatementNode.getThenStatement().accept(this, param)).append("}else{\n");
        ifBuilder.append(ifThenElseStatementNode.getElseStatement().accept(this, param)).append("\n}\n");
        return ifBuilder.toString();
    }

    @Override
    public String visit(BinaryExpression binaryExpressionNode, Scope param) {
        StringBuilder binaryBuilder = new StringBuilder();
        binaryBuilder.append(binaryExpressionNode.getLeftOperand().accept(this, param)).append(' ');
        binaryBuilder.append(mapOperand(binaryExpressionNode.getOp())).append(' ');
        binaryBuilder.append(binaryExpressionNode.getRightOperand().accept(this, param));
        return binaryBuilder.toString();
    }

    @Override
    public String visit(UminusExpression uminusExpressionNode, Scope param) {
        StringBuilder uminusBuilder = new StringBuilder();
        uminusBuilder.append('-').append(uminusExpressionNode.getExpression().accept(this, param));
        return uminusBuilder.toString();
    }

    @Override
    public String visit(DoubleConst doubleConstNode, Scope param) {
        return String.valueOf(doubleConstNode.getDoubleValue());
    }

    @Override
    public String visit(IntegerConst integerConstNode, Scope param) {
        return String.valueOf(integerConstNode.getIntValue());
    }

    @Override
    public String visit(StringConst stringConstNode, Scope param) {
        return "\"" + stringConstNode.getStringValue() + "\"";
    }

    @Override
    public String visit(NotExpression notExpressionNode, Scope param) {
        StringBuilder notBuilder = new StringBuilder();
        notBuilder.append('(').append('!').append(notExpressionNode.getExpression().accept(this, param));
        notBuilder.append(')');
        return notBuilder.toString();
    }

    @Override
    public String visit(TrueExpression trueExpressionNode, Scope param) {
        return String.valueOf(trueExpressionNode.getValue());
    }

    @Override
    public String visit(FalseExpression falseExpressionNode, Scope param) {
        return String.valueOf(falseExpressionNode.getValue());
    }

    @Override
    public String visit(RelationalExpression relationalExpressionNode, Scope param) {
        StringBuilder relBuilder = new StringBuilder();
        relBuilder.append(relationalExpressionNode.getLeftOperand().accept(this, param)).append(' ');
        relBuilder.append(mapOperand(relationalExpressionNode.getRelOp())).append(' ');
        relBuilder.append(relationalExpressionNode.getRightOperand().accept(this, param));
        return relBuilder.toString();
    }

    @Override
    public String visit(AssignStatement assignStatementNode, Scope param) {
        StringBuilder assignBuilder = new StringBuilder();
        assignBuilder.append(assignStatementNode.getIdentifier().accept(this, param)).append(' ').append('=');
        assignBuilder.append(' ').append(assignStatementNode.getExpression().accept(this, param)).append(";\n");
        return assignBuilder.toString();
    }

    private static String mapType(String type){
        switch(type){
            case "bool": case "int":
                return "%d";
            case "double":
                return "%lf";
            case "string":
                return "%s";
                default:
                    return "%d";
        }
    }

    private static String mapOperand(String op){
        switch(op){
            case "PLUS":
                return "+";
            case "MINUS":
                return "-";
            case "TIMES":
                return "*";
            case "DIV":
                return "/";
            case "GT":
                return ">";
            case "LT":
                return "<";
            case "GE":
                return ">=";
            case "LE":
                return "<=";
            case "EQ":
                return "==";
            case "AND":
                return "&&";
            case "OR":
                return "||";
                default:
                    return "";
        }
    }

    private static String toCType(String type){
        if(type.equals("string")){
            type = "char";
        }
        return type;
    }

    private String compactCode(List<? extends YasplNode> list, Scope scope){
        return list.stream().map(l -> l.accept(this, scope)).reduce("", String::concat);
    }

}
