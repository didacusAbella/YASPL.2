package com.didacusabella.yaspl.visitor;


import com.didacusabella.yaspl.semantic.*;
import com.didacusabella.yaspl.syntax.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public class SemanticVisitor implements Visitor<ReturnType, Logger> {

    private SymbolTable symbolTable;

    public SemanticVisitor(SymbolTable table) {
        super();
        this.symbolTable = table;
    }

    @Override
    public ReturnType visit(Program programNode, Logger param) {
        this.symbolTable.enterScope();
        programNode.getDeclarations().forEach(d -> d.accept(this, param));
        programNode.getStatements().forEach(s -> s.accept(this, param));
        if(this.checkAll(programNode.getDeclarations()) && this.checkAll(programNode.getStatements()))
            programNode.setNodeType(ReturnType.VOID);
        else {
            programNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Program error", programNode));
        }
        programNode.attachScope(this.symbolTable.getCurrentScope());
        this.symbolTable.exitScope();
        return programNode.getNodeType();
    }

    @Override
    public ReturnType visit(VariableDeclaration variableDeclarationNode, Logger param) {
        variableDeclarationNode.getType().accept(this, param);
        variableDeclarationNode.getVariables().forEach(v -> {
            int variableAddress = this.symbolTable.findAddress(v.getIdentifier().getName());
            if(this.symbolTable.probe(variableAddress))
                v.setNodeType(this.symbolTable.getCurrentScope().get(variableAddress).getReturnType());
            else
                v.setNodeType(ReturnType.UNDEFINED);
        });
        if(this.allUndefined(variableDeclarationNode.getVariables()) && this.isUndefined(variableDeclarationNode.getType())) {
            ReturnType varType = variableDeclarationNode.getType().getNodeType();
            variableDeclarationNode.setNodeType(varType);
            variableDeclarationNode.getVariables().forEach(v -> {
                int tmpAddress = this.symbolTable.findAddress(v.getIdentifier().getName());
                this.symbolTable.addId(tmpAddress, new VariableSymbol(varType));
            });
        }else{
            param.severe(ErrorGenerator.generateError("Variable declaration error", variableDeclarationNode));
            variableDeclarationNode.setNodeType(ReturnType.UNDEFINED);
        }
        return variableDeclarationNode.getNodeType();
    }

    @Override
    public ReturnType visit(FunctionDeclaration functionDeclarationNode, Logger param) {
        functionDeclarationNode.getIdentifier().accept(this, param);
        //Se Identifier è uguale ad UNDEFINED
        if(!this.isUndefined(functionDeclarationNode.getIdentifier())){
            int functionAddress = this.symbolTable.findAddress(functionDeclarationNode.getIdentifier().getName());
            FunctionSymbol fs = new FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
            this.symbolTable.addId(functionAddress,fs);
            this.symbolTable.enterScope();
            functionDeclarationNode.getVariableDeclarations().forEach(v -> v.accept(this, param));
            functionDeclarationNode.getParameterDeclarations().forEach(p -> p.accept(this, param));
            functionDeclarationNode.getBody().accept(this, param);
            if(this.checkAll(functionDeclarationNode.getVariableDeclarations()) && this.checkAll(functionDeclarationNode.getParameterDeclarations())
                    && this.isUndefined(functionDeclarationNode.getBody())) {
                functionDeclarationNode.setNodeType(ReturnType.VOID);
                fs.setInputDomain(functionDeclarationNode.functionDomain());
                fs.setOutputDomain(functionDeclarationNode.functionCodomain());
                /*Patch parameter*/
                for(ParameterDeclaration p : functionDeclarationNode.getParameterDeclarations()){
                    for(VariableDeclaration vd : p.getVariableDeclarationList()){
                        for(Variable v : vd.getVariables()){
                            int address = this.symbolTable.findAddress(v.getIdentifier().getName());
                            VariableSymbol vs = (VariableSymbol)this.symbolTable.getCurrentScope().get(address);
                            vs.setVarType(VariableType.OUTPUT);
                        }
                    }
                }
            } else {
                functionDeclarationNode.setNodeType(ReturnType.UNDEFINED);
                param.severe(ErrorGenerator.generateError("Function declaration error", functionDeclarationNode));
            }
            functionDeclarationNode.attachScope(this.symbolTable.getCurrentScope());
            this.symbolTable.exitScope();
        }else{
            param.severe(ErrorGenerator.generateError("Function already defined", functionDeclarationNode));
            functionDeclarationNode.setNodeType(ReturnType.UNDEFINED);
        }
        return functionDeclarationNode.getNodeType();
    }

    @Override
    public ReturnType visit(Variable variableNode, Logger param) {
        variableNode.getIdentifier().accept(this, param);
        if(this.isUndefined(variableNode.getIdentifier())){
            variableNode.setNodeType(variableNode.getIdentifier().getNodeType());
        }else {
            variableNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Variable not defined", variableNode));
        }
        return variableNode.getNodeType();
    }

    @Override
    public ReturnType visit(Type typeNode, Logger param) {
        if(typeNode.getTypeName().equals("int") || typeNode.getTypeName().equals("double") || typeNode.getTypeName().equals("string")
                || typeNode.getTypeName().equals("bool")) {
            typeNode.setNodeType(ReturnType.getEnumFor(typeNode.getTypeName()));
        }
        else {
            typeNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Type not permitted", typeNode));
        }
        return typeNode.getNodeType();
    }

    @Override
    public ReturnType visit(Identifier identifierNode, Logger param) {
        int address = this.symbolTable.findAddress(identifierNode.getName());
        Scope candidate = this.symbolTable.lookup(address);
        ReturnType tableType = (candidate != null) ? candidate.get(address).getReturnType() : ReturnType.UNDEFINED;
        identifierNode.setNodeType(tableType);
        return identifierNode.getNodeType();
    }

    @Override
    public ReturnType visit(ParameterDeclaration parameterDeclarationNode, Logger param) {
        parameterDeclarationNode.getVariableDeclarationList().forEach(o -> o.accept(this, param));
        if(this.checkAll(parameterDeclarationNode.getVariableDeclarationList())){
            parameterDeclarationNode.setNodeType(ReturnType.VOID);
        }else{
            param.severe(ErrorGenerator.generateError("Parameter declaration error", parameterDeclarationNode));
            parameterDeclarationNode.setNodeType(ReturnType.UNDEFINED);
        }
        return parameterDeclarationNode.getNodeType();
    }

    @Override
    public ReturnType visit(Body bodyNode, Logger param) {
        bodyNode.getVariableDeclarationList().forEach(v -> v.accept(this, param));
        bodyNode.getStatements().forEach(s -> s.accept(this, param));
        if(this.checkAll(bodyNode.getVariableDeclarationList()) && this.checkAll(bodyNode.getStatements()))
            bodyNode.setNodeType(ReturnType.VOID);
        else {
            param.severe(ErrorGenerator.generateError("Error at body level", bodyNode));
            bodyNode.setNodeType(ReturnType.UNDEFINED);
        }
        return bodyNode.getNodeType();
    }

    @Override
    public ReturnType visit(ReadStatement readStatementNode, Logger param) {
        readStatementNode.getIdentifierList().forEach(i -> i.accept(this, param));
        readStatementNode.getTypeList().forEach(t -> t.accept(this, param));
        if(this.checkAll(readStatementNode.getIdentifierList()) && this.checkAll(readStatementNode.getTypeList())){
            if(readStatementNode.checkInputValidity()){
                readStatementNode.setNodeType(ReturnType.VOID);
            }else{
                param.severe(ErrorGenerator.generateError(String.format("Input expected: %s but found %s",
                        readStatementNode.typeDomain(), readStatementNode.variableDomain()), readStatementNode));
                readStatementNode.setNodeType(ReturnType.UNDEFINED);
            }
        }else {
            param.severe(ErrorGenerator.generateError("Read statement Error", readStatementNode));
            readStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return readStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(WriteStatement writeStatementNode, Logger param) {
        writeStatementNode.getExpressions().forEach(e -> e.accept(this, param));
        if(this.checkAll(writeStatementNode.getExpressions()))
            writeStatementNode.setNodeType(ReturnType.VOID);
        else {
            writeStatementNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Write statement error", writeStatementNode));
        }
        return writeStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(FunctionCall functionCallNode, Logger param) {
        functionCallNode.getFunctionName().accept(this, param);
        functionCallNode.getExpressions().forEach(e -> e.accept(this, param));
        functionCallNode.getVariableList().forEach(v -> v.accept(this, param));
        if(this.isUndefined(functionCallNode.getFunctionName())) {
            int funcAddress = this.symbolTable.findAddress(functionCallNode.getFunctionName().getName());
            FunctionSymbol fs = (FunctionSymbol)this.symbolTable.getCurrentScope().get(funcAddress);
            if(!fs.getInputDomain().equals(functionCallNode.getDomain())){
                param.severe(ErrorGenerator.generateError(String.format("Input Expected %s but found %s",
                        fs.getInputDomain(), functionCallNode.getDomain()), functionCallNode));
                functionCallNode.setNodeType(ReturnType.UNDEFINED);
            }else if(!fs.getOutputDomain().equals(functionCallNode.getCodomain())){
                param.severe(ErrorGenerator.generateError(String.format("Output Expected %s but found %s",
                        fs.getInputDomain(), functionCallNode.getDomain()), functionCallNode));
                functionCallNode.setNodeType(ReturnType.UNDEFINED);
            } else {
                functionCallNode.setNodeType(ReturnType.VOID);
            }
        }else {
            param.severe(ErrorGenerator.generateError("Identifier not declared", functionCallNode));
            functionCallNode.setNodeType(ReturnType.UNDEFINED);
        }
        return functionCallNode.getNodeType();
    }

    @Override
    public ReturnType visit(CompositeStatement compositeStatementNode, Logger param) {
        compositeStatementNode.getStatementList().forEach(s -> s.accept(this, param));
        if(this.checkAll(compositeStatementNode.getStatementList()))
            compositeStatementNode.setNodeType(ReturnType.VOID);
        else {
            compositeStatementNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Composite Statement error", compositeStatementNode));
        }
        return compositeStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(WhileStatement whileStatementNode, Logger param) {
        whileStatementNode.getWhileCondition().accept(this, param);
        whileStatementNode.getWhileStatement().accept(this, param);
        if(this.isUndefined(whileStatementNode.getWhileCondition()) && this.isUndefined(whileStatementNode.getWhileStatement())){
            if(whileStatementNode.getWhileCondition().getNodeType() != ReturnType.BOOLEAN){
                whileStatementNode.setNodeType(ReturnType.UNDEFINED);
                param.severe(ErrorGenerator.generateError("While condition not boolean", whileStatementNode));
            }else{
                whileStatementNode.setNodeType(ReturnType.VOID);
            }
        }else {
            param.severe(ErrorGenerator.generateError("While statement error", whileStatementNode));
            whileStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return whileStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(IfThenStatement ifThenStatementNode, Logger param) {
        ifThenStatementNode.getIfCondition().accept(this, param);
        ifThenStatementNode.getThenStatement().accept(this, param);
        if(this.isUndefined(ifThenStatementNode.getIfCondition()) && this.isUndefined(ifThenStatementNode.getThenStatement())){
            if(ifThenStatementNode.getIfCondition().getNodeType() == ReturnType.BOOLEAN){
                ifThenStatementNode.setNodeType(ReturnType.VOID);
            }else {
                param.severe(ErrorGenerator.generateError("Type mismatch. Expected boolean", ifThenStatementNode));
                ifThenStatementNode.setNodeType(ReturnType.UNDEFINED);
            }
        }else {
            param.severe(ErrorGenerator.generateError("If statement error", ifThenStatementNode));
            ifThenStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return ifThenStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(IfThenElseStatement ifThenElseStatementNode, Logger param) {
        ifThenElseStatementNode.getIfCondition().accept(this, param);
        ifThenElseStatementNode.getThenStatement().accept(this, param);
        ifThenElseStatementNode.getElseStatement().accept(this, param);
        if(this.isUndefined(ifThenElseStatementNode.getIfCondition()) && this.isUndefined(ifThenElseStatementNode.getThenStatement())
                && this.isUndefined(ifThenElseStatementNode.getElseStatement())){
            if(ifThenElseStatementNode.getIfCondition().getNodeType() != ReturnType.BOOLEAN){
                param.severe(ErrorGenerator.generateError("Type mismatch. Expected boolean", ifThenElseStatementNode));
                ifThenElseStatementNode.setNodeType(ReturnType.UNDEFINED);
            }else {
                ifThenElseStatementNode.setNodeType(ReturnType.VOID);
            }
        }else {
            param.severe(ErrorGenerator.generateError("If Else statement error", ifThenElseStatementNode));
            ifThenElseStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return ifThenElseStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(BinaryExpression binaryExpressionNode, Logger param) {
        binaryExpressionNode.getLeftOperand().accept(this, param);
        binaryExpressionNode.getRightOperand().accept(this ,param);
        if(this.isUndefined(binaryExpressionNode.getLeftOperand()) && this.isUndefined(binaryExpressionNode.getRightOperand())){
            ReturnType leftType = binaryExpressionNode.getLeftOperand().getNodeType();
            ReturnType rightType = binaryExpressionNode.getRightOperand().getNodeType();
            if((leftType == ReturnType.DOUBLE || leftType == ReturnType.INTEGER)
                    && (rightType == ReturnType.DOUBLE || rightType == ReturnType.INTEGER)){
                if(leftType == ReturnType.DOUBLE || rightType == ReturnType.DOUBLE){
                    binaryExpressionNode.setNodeType(ReturnType.DOUBLE);
                }else {
                    binaryExpressionNode.setNodeType(ReturnType.INTEGER);
                }
            }else {
                param.severe(ErrorGenerator.generateError("Type mismatch, Expected Integer or Double", binaryExpressionNode));
                binaryExpressionNode.setNodeType(ReturnType.UNDEFINED);
            }
        }else {
            param.severe(ErrorGenerator.generateError("Binary Expression error", binaryExpressionNode));
            binaryExpressionNode.setNodeType(ReturnType.UNDEFINED);
        }
        return binaryExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(UminusExpression uminusExpressionNode, Logger param) {
        uminusExpressionNode.getExpression().accept(this, param);
        if(this.isUndefined(uminusExpressionNode.getExpression())){
            if(uminusExpressionNode.getExpression().getNodeType() == ReturnType.DOUBLE){
                uminusExpressionNode.setNodeType(ReturnType.DOUBLE);
            }else if(uminusExpressionNode.getExpression().getNodeType() == ReturnType.DOUBLE){
                uminusExpressionNode.setNodeType(ReturnType.INTEGER);
            }else {
                param.severe(ErrorGenerator.generateError("Type mismatch. expected Double or Integer", uminusExpressionNode));
                uminusExpressionNode.setNodeType(ReturnType.UNDEFINED);
            }
        }else {
            param.severe(ErrorGenerator.generateError("Uminus Expression error", uminusExpressionNode));
            uminusExpressionNode.setNodeType(ReturnType.UNDEFINED);
        }
        return uminusExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(DoubleConst doubleConstNode, Logger param) {
        doubleConstNode.setNodeType(ReturnType.DOUBLE);
        return doubleConstNode.getNodeType();
    }

    @Override
    public ReturnType visit(IntegerConst integerConstNode, Logger param) {
        integerConstNode.setNodeType(ReturnType.INTEGER);
        return integerConstNode.getNodeType();
    }

    @Override
    public ReturnType visit(StringConst stringConstNode, Logger param) {
        stringConstNode.setNodeType(ReturnType.STRING);
        return stringConstNode.getNodeType();
    }

    @Override
    public ReturnType visit(NotExpression notExpressionNode, Logger param) {
        notExpressionNode.getExpression().accept(this, param);
        if(this.isUndefined(notExpressionNode.getExpression())){
            if(notExpressionNode.getExpression().getNodeType() == ReturnType.BOOLEAN){
                notExpressionNode.setNodeType(ReturnType.BOOLEAN);
            }else {
                param.severe(ErrorGenerator.generateError("Type mismatch. Expected Boolean", notExpressionNode));
                notExpressionNode.setNodeType(ReturnType.UNDEFINED);
            }
        }else {
            param.severe(ErrorGenerator.generateError("Not Expression error", notExpressionNode));
            notExpressionNode.setNodeType(ReturnType.UNDEFINED);
        }
        return notExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(TrueExpression trueExpressionNode, Logger param) {
        trueExpressionNode.setNodeType(ReturnType.BOOLEAN);
        return trueExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(FalseExpression falseExpressionNode, Logger param) {
        falseExpressionNode.setNodeType(ReturnType.BOOLEAN);
        return falseExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(RelationalExpression relationalExpressionNode, Logger param) {
        relationalExpressionNode.getLeftOperand().accept(this, param);
        relationalExpressionNode.getRightOperand().accept(this, param);
        if(this.isUndefined(relationalExpressionNode.getLeftOperand()) && this.isUndefined(relationalExpressionNode.getRightOperand())){
            ReturnType leftType = relationalExpressionNode.getLeftOperand().getNodeType();
            ReturnType rightType = relationalExpressionNode.getRightOperand().getNodeType();
            if(relationalExpressionNode.getRelOp().equals("AND") || relationalExpressionNode.getRelOp().equals("OR")){
                if(leftType == ReturnType.BOOLEAN && rightType == ReturnType.BOOLEAN){
                    relationalExpressionNode.setNodeType(ReturnType.BOOLEAN);
                }else {
                    param.severe(ErrorGenerator.generateError("Type mismatch. Expected boolean", relationalExpressionNode));
                    relationalExpressionNode.setNodeType(ReturnType.UNDEFINED);
                }
            }else {
                if((leftType == ReturnType.DOUBLE || leftType == ReturnType.INTEGER)
                        && (rightType == ReturnType.INTEGER || rightType == ReturnType.DOUBLE)){
                    relationalExpressionNode.setNodeType(ReturnType.BOOLEAN);
                }else {
                    param.severe(ErrorGenerator.generateError("Type mismatch. Expected Integer or Double", relationalExpressionNode));
                    relationalExpressionNode.setNodeType(ReturnType.UNDEFINED);
                }
            }
        }else {
            param.severe(ErrorGenerator.generateError("Relational Expression error", relationalExpressionNode));
        }
        return relationalExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(AssignStatement assignStatementNode, Logger param) {
        assignStatementNode.getIdentifier().accept(this, param);
        assignStatementNode.getExpression().accept(this, param);
        if(this.isUndefined(assignStatementNode.getExpression()) && this.isUndefined(assignStatementNode.getIdentifier())){
            ReturnType identifier = assignStatementNode.getIdentifier().getNodeType();
            ReturnType expression = assignStatementNode.getExpression().getNodeType();
            if((identifier == ReturnType.DOUBLE || identifier == ReturnType.INTEGER)
                    && (expression == ReturnType.INTEGER || expression == ReturnType.DOUBLE)){
                assignStatementNode.setNodeType(ReturnType.VOID);
            }else {
                param.severe(ErrorGenerator.generateError("Type mismatch. Expected Integer or Double", assignStatementNode));
                assignStatementNode.setNodeType(ReturnType.UNDEFINED);
            }
        }else {
            param.severe(ErrorGenerator.generateError("Assignment expression error", assignStatementNode));
            assignStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return assignStatementNode.getNodeType();
    }

    private boolean checkAll(List<? extends YasplNode> list){
        return list.stream().allMatch(node -> node.getNodeType() != ReturnType.UNDEFINED);
    }

    private boolean allUndefined(List<? extends YasplNode> list){
        return list.stream().allMatch(node -> node.getNodeType() == ReturnType.UNDEFINED);
    }

    private boolean isUndefined(YasplNode node){
        return node.getNodeType() != ReturnType.UNDEFINED;
    }

}

