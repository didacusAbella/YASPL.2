package com.didacusabella.yaspl.visitor;


import com.didacusabella.yaspl.semantic.*;
import com.didacusabella.yaspl.syntax.*;
import java.util.logging.Logger;


public class SemanticVisitor implements Visitor<ReturnType, Logger> {

    private SymbolTable symbolTable;

    public SemanticVisitor(SymbolTable table) {
        super();
        this.symbolTable = table;
    }

    @Override
    public ReturnType visit(Program programNode, Logger param) {
        this.symbolTable.enterScope(new Scope());
        programNode.getDeclarations().forEach(d -> d.accept(this, param));
        programNode.getStatements().forEach(s -> s.accept(this, param));
        if(programNode.checkType())
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
        variableDeclarationNode.getVariables().forEach(v -> v.accept(this, param));
        if(variableDeclarationNode.checkType()) {
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
        if(!functionDeclarationNode.getIdentifier().checkType()){
            int functionAddress = this.symbolTable.findAddress(functionDeclarationNode.getIdentifier().getName());
            FunctionSymbol fs = new FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
            this.symbolTable.addId(functionAddress,fs);
            this.symbolTable.enterScope(new Scope());
            functionDeclarationNode.getVariableDeclarations().forEach(v -> v.accept(this, param));
            functionDeclarationNode.getParameterDeclarations().forEach(p -> p.accept(this, param));
            functionDeclarationNode.getBody().accept(this, param);
            if(functionDeclarationNode.checkType()) {
                functionDeclarationNode.setNodeType(ReturnType.VOID);
                fs.setInputDomain(functionDeclarationNode.domainString());
                fs.setOutputDomain(functionDeclarationNode.codomainString());
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
        int address = this.symbolTable.findAddress(variableNode.getIdentifier().getName());
        if(!this.symbolTable.probe(address)){
            variableNode.setNodeType(ReturnType.UNDEFINED);
            return variableNode.getNodeType();
        }else {
            variableNode.getIdentifier().accept(this, param);
            if(variableNode.checkType()) {
                variableNode.setNodeType(variableNode.getIdentifier().getNodeType());
            } else {
                variableNode.setNodeType(ReturnType.UNDEFINED);
            }
        }
        return variableNode.getNodeType();
    }

    @Override
    public ReturnType visit(Type typeNode, Logger param) {
        if(typeNode.checkType())
            typeNode.setNodeType(ReturnType.getEnumFor(typeNode.getTypeName()));
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
        if(candidate != null) {
            identifierNode.setNodeType(candidate.get(address).getReturnType());
        }
        else {
            identifierNode.setNodeType(ReturnType.UNDEFINED);
        }
        return identifierNode.getNodeType();
    }

    @Override
    public ReturnType visit(ParameterDeclaration parameterDeclarationNode, Logger param) {
        parameterDeclarationNode.getVariableDeclarationList().forEach(o -> o.accept(this, param));
        if(parameterDeclarationNode.checkType()){
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
        if(bodyNode.checkType())
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
        int sizeIdentifiers = readStatementNode.getIdentifierList().size();
        int sizeTypes = readStatementNode.getTypeList().size();
        if(!readStatementNode.checkAll(readStatementNode.getIdentifierList())){
            param.severe(ErrorGenerator.generateError("Variable not defined", readStatementNode));
            readStatementNode.setNodeType(ReturnType.UNDEFINED);
        }else if(!readStatementNode.checkAll(readStatementNode.getTypeList())){
            param.severe(ErrorGenerator.generateError("Types not permitted", readStatementNode));
            readStatementNode.setNodeType(ReturnType.UNDEFINED);
        }else if(sizeIdentifiers != sizeTypes){
            param.severe(ErrorGenerator.generateError("variable or types are different in number",
                    readStatementNode));
            readStatementNode.setNodeType(ReturnType.UNDEFINED);
        } else if(!readStatementNode.checkType()) {
            param.severe(ErrorGenerator.generateError("Read incompatibles", readStatementNode));
            readStatementNode.setNodeType(ReturnType.UNDEFINED);
        } else {
            readStatementNode.setNodeType(ReturnType.VOID);
        }
        return readStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(WriteStatement writeStatementNode, Logger param) {
        writeStatementNode.getExpressions().forEach(e -> e.accept(this, param));
        if(writeStatementNode.checkType())
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
        if(functionCallNode.getFunctionName().checkType()) {
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
        if(compositeStatementNode.checkType())
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
        if(whileStatementNode.checkType()){
            whileStatementNode.setNodeType(ReturnType.VOID);
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
        if(ifThenStatementNode.checkType()){
            ifThenStatementNode.setNodeType(ReturnType.VOID);
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
        if(ifThenElseStatementNode.checkType()){
            ifThenElseStatementNode.setNodeType(ReturnType.VOID);
        }else {
            param.severe(ErrorGenerator.generateError("If Else error", ifThenElseStatementNode));
            ifThenElseStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return ifThenElseStatementNode.getNodeType();
    }

    @Override
    public ReturnType visit(BinaryExpression binaryExpressionNode, Logger param) {
        binaryExpressionNode.getLeftOperand().accept(this, param);
        binaryExpressionNode.getRightOperand().accept(this ,param);
        if(binaryExpressionNode.checkType()){
            if(binaryExpressionNode.getLeftOperand().getNodeType() == ReturnType.DOUBLE
                    || binaryExpressionNode.getRightOperand().getNodeType() == ReturnType.DOUBLE){
                binaryExpressionNode.setNodeType(ReturnType.DOUBLE);
            }else
                binaryExpressionNode.setNodeType(ReturnType.INTEGER);
        }else{
            binaryExpressionNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Type mismatch", binaryExpressionNode));
        }
        return binaryExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(UminusExpression uminusExpressionNode, Logger param) {
        uminusExpressionNode.getExpression().accept(this, param);
        if(uminusExpressionNode.checkType()){
            uminusExpressionNode.setNodeType(uminusExpressionNode.getExpression().getNodeType());
        }else {
            param.severe(ErrorGenerator.generateError("Type mismatch", uminusExpressionNode));
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
        if(notExpressionNode.checkType()){
            notExpressionNode.setNodeType(ReturnType.BOOLEAN);
        }else {
            param.severe(ErrorGenerator.generateError("Type mismatch", notExpressionNode));
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
        if(relationalExpressionNode.checkType()){
            relationalExpressionNode.setNodeType(ReturnType.BOOLEAN);
        }else {
            relationalExpressionNode.setNodeType(ReturnType.UNDEFINED);
            param.severe(ErrorGenerator.generateError("Type mismatch", relationalExpressionNode));
        }
        return relationalExpressionNode.getNodeType();
    }

    @Override
    public ReturnType visit(AssignStatement assignStatementNode, Logger param) {
        assignStatementNode.getIdentifier().accept(this, param);
        assignStatementNode.getExpression().accept(this, param);
        if(assignStatementNode.checkType()){
            assignStatementNode.setNodeType(ReturnType.VOID);
        }else {
            param.severe(ErrorGenerator.generateError("Type mismatch", assignStatementNode));
            assignStatementNode.setNodeType(ReturnType.UNDEFINED);
        }
        return assignStatementNode.getNodeType();
    }

}

