package com.didacusabella.yaspl.visitor;

import com.didacusabella.yaspl.syntax.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SyntaxVisitor implements Visitor<Element, Void>{

    private Document xmlDocument;

    public SyntaxVisitor() {
        super();
        this.createDocument();
    }

    public void appendRoot(Element el){
        this.xmlDocument.appendChild(el);
    }


    private void createDocument(){
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            this.xmlDocument = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void toXml(){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(this.xmlDocument);
            StreamResult result = new StreamResult(new File(System.getProperty("user.home").concat("/output.xml")));
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Element visit(Program programNode, Void param) {
        Element el = this.xmlDocument.createElement("ProgramOp");
        programNode.getDeclarations().forEach(d -> el.appendChild(d.accept(this, param)));
        programNode.getStatements().forEach(s -> el.appendChild(s.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(VariableDeclaration variableDeclarationNode, Void param) {
        Element el = this.xmlDocument.createElement("VarDeclOp");
        el.appendChild(variableDeclarationNode.getType().accept(this, param));
        variableDeclarationNode.getVariables().forEach(v -> el.appendChild(v.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(FunctionDeclaration functionDeclarationNode, Void param) {
        Element el = this.xmlDocument.createElement("ProcDeclOp");
        el.setAttribute("name", functionDeclarationNode.getIdentifier().getName());
        functionDeclarationNode.getVariableDeclarations().forEach(v -> el.appendChild(v.accept(this, param)));
        functionDeclarationNode.getParameterDeclarations().forEach(p -> el.appendChild(p.accept(this, param)));
        el.appendChild(functionDeclarationNode.getBody().accept(this, param));
        return el;
    }

    @Override
    public Element visit(Variable variableNode, Void param) {
        Element el = this.xmlDocument.createElement("VarOp");
        el.appendChild(variableNode.getIdentifier().accept(this, param));
        return el;
    }

    @Override
    public Element visit(Type typeNode, Void param) {
        Element el = this.xmlDocument.createElement("Type");
        el.setAttribute("value",typeNode.getTypeName());
        return el;
    }

    @Override
    public Element visit(Identifier identifierNode, Void param) {
        Element el = this.xmlDocument.createElement("IdentifierOp");
        el.setAttribute("lexem", identifierNode.getName());
        return el;
    }

    @Override
    public Element visit(ParameterDeclaration parameterDeclarationNode, Void param) {
        Element el = this.xmlDocument.createElement("ParDeclOp");
        parameterDeclarationNode.getVariableDeclarationList().forEach(v -> el.appendChild(v.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(Body bodyNode, Void param) {
        Element el =  this.xmlDocument.createElement("BodyOp");
        bodyNode.getStatements().forEach(s -> el.appendChild(s.accept(this, param)));
        bodyNode.getVariableDeclarationList().forEach(v -> el.appendChild(v.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(ReadStatement readStatementNode, Void param) {
        Element el =  this.xmlDocument.createElement("ReadOp");
        readStatementNode.getIdentifierList().forEach(i -> el.appendChild(i.accept(this, param)));
        readStatementNode.getTypeList().forEach(t -> el.appendChild(t.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(WriteStatement writeStatementNode, Void param) {
        Element el = this.xmlDocument.createElement("WriteOp");
        writeStatementNode.getExpressions().forEach(e -> el.appendChild(e.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(FunctionCall functionCallNode, Void param) {
        Element el = this.xmlDocument.createElement("CallOp");
        el.setAttribute("name", functionCallNode.getFunctionName().getName());
        functionCallNode.getExpressions().forEach(e -> el.appendChild(e.accept(this, param)));
        functionCallNode.getVariableList().forEach(v -> el.appendChild(v.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(CompositeStatement compositeStatementNode, Void param) {
        Element el =  this.xmlDocument.createElement("CompStatOp");
        compositeStatementNode.getStatementList().forEach(s -> el.appendChild(s.accept(this, param)));
        return el;
    }

    @Override
    public Element visit(WhileStatement whileStatementNode, Void param) {
        Element el = this.xmlDocument.createElement("WhileOp");
        el.appendChild(whileStatementNode.getWhileCondition().accept(this, param));
        el.appendChild(whileStatementNode.getWhileStatement().accept(this, param));
        return el;
    }

    @Override
    public Element visit(IfThenStatement ifThenStatementNode, Void param) {
        Element el = this.xmlDocument.createElement("IfThenOp");
        el.appendChild(ifThenStatementNode.getIfCondition().accept(this, param));
        el.appendChild(ifThenStatementNode.getThenStatement().accept(this, param));
        return el;
    }

    @Override
    public Element visit(IfThenElseStatement ifThenElseStatementNode, Void param) {
        Element el = this.xmlDocument.createElement("IfThenElseOp");
        el.appendChild(ifThenElseStatementNode.getIfCondition().accept(this, param));
        el.appendChild(ifThenElseStatementNode.getThenStatement().accept(this, param));
        el.appendChild(ifThenElseStatementNode.getElseStatement().accept(this, param));
        return el;
    }

    @Override
    public Element visit(BinaryExpression binaryExpressionNode, Void param) {
        Element el = this.xmlDocument.createElement("BinOp");
        el.setAttribute("operator", binaryExpressionNode.getOp());
        el.appendChild(binaryExpressionNode.getLeftOperand().accept(this, param));
        el.appendChild(binaryExpressionNode.getRightOperand().accept(this, param));
        return el;
    }

    @Override
    public Element visit(UminusExpression uminusExpressionNode, Void param) {
        Element el = this.xmlDocument.createElement("UMinusOp");
        el.appendChild(uminusExpressionNode.getExpression().accept(this, param));
        return el;
    }

    @Override
    public Element visit(DoubleConst doubleConstNode, Void param) {
        Element el = this.xmlDocument.createElement("DoubleConst");
        el.setAttribute("value", String.valueOf(doubleConstNode.getDoubleValue()));
        return el;
    }

    @Override
    public Element visit(IntegerConst integerConstNode, Void param) {
        Element el = this.xmlDocument.createElement("IntConst");
        el.setAttribute("value", String.valueOf(integerConstNode.getIntValue()));
        return el;
    }

    @Override
    public Element visit(StringConst stringConstNode, Void param) {
        Element el = this.xmlDocument.createElement("StringConst");
        el.setAttribute("value", String.valueOf(stringConstNode.getStringValue()));
        return el;
    }

    @Override
    public Element visit(NotExpression notExpressionNode, Void param) {
        Element el = this.xmlDocument.createElement("NotOp");
        el.appendChild(notExpressionNode.getExpression().accept(this, param));
        return el;
    }

    @Override
    public Element visit(TrueExpression trueExpressionNode, Void param) {
        Element el = this.xmlDocument.createElement("TrueConst");
        el.setAttribute("value", String.valueOf(trueExpressionNode.getValue()));
        return el;
    }

    @Override
    public Element visit(FalseExpression falseExpressionNode, Void param) {
        Element el = this.xmlDocument.createElement("FalseConst");
        el.setAttribute("value", String.valueOf(falseExpressionNode.getValue()));
        return el;
    }

    @Override
    public Element visit(RelationalExpression relationalExpressionNode, Void param) {
        Element el = this.xmlDocument.createElement("Relop");
        el.setAttribute("operator", relationalExpressionNode.getRelOp());
        el.appendChild(relationalExpressionNode.getLeftOperand().accept(this, param));
        el.appendChild(relationalExpressionNode.getRightOperand().accept(this, param));
        return el;
    }

    @Override
    public Element visit(AssignStatement assignStatementNode, Void param) {
        Element el = this.xmlDocument.createElement("AssignOp");
        el.appendChild(assignStatementNode.getIdentifier().accept(this, param));
        el.appendChild(assignStatementNode.getExpression().accept(this, param));
        return el;
    }
}
