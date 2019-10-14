package com.didacusabella.yaspl.template;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author didacus
 */
public class XmlTemplate implements Template {

  private final Document document;

  public XmlTemplate() throws ParserConfigurationException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    this.document = docBuilder.newDocument();
  }

  @Override
  public void render(String filePath) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(this.document);
      StreamResult result = new StreamResult(filePath);
      transformer.transform(source, result);
    } catch (TransformerConfigurationException ex) {
      System.err.println("Check configuration for generate XML");
    } catch (TransformerException ex) {
      System.err.println("Error during transforming");
    }
  }
  
  public Document getDocument() {
    return this.document;
  }

}
