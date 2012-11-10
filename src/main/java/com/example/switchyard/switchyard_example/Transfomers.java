package com.example.switchyard.switchyard_example;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.StringReader;

public final class Transfomers {

	@Transformer(to = "{urn:com.example.switchyard:switchyard-example:0.0.1-SNAPSHOT}sayHelloResponse")
	public Element transformStringToSayHelloResponse(String from) {
		StringBuffer ackXml = new StringBuffer()
        .append("<hello:sayHelloResponse xmlns:hello=\"urn:com.example.switchyard:switchyard-example:0.0.1-SNAPSHOT\">")
        .append("<return>" + from + "</return>")
        .append("</hello:sayHelloResponse>");
 
        return toElement(ackXml.toString());
	}

	@Transformer(from = "{urn:com.example.switchyard:switchyard-example:0.0.1-SNAPSHOT}sayHello")
	public String transformSayHelloToString(Element from) {
		return new String(getElementValue(from, "urn:string"));
	}
	
	
	
	private String getElementValue(Element parent, String elementName) {
        String value = null;
        NodeList nodes = parent.getElementsByTagName(elementName);
        if (nodes.getLength() > 0) {
            value = nodes.item(0).getChildNodes().item(0).getNodeValue();
        }
        return value;
    }
    private Element toElement(String xml) {
        DOMResult dom = new DOMResult();
        try {
            TransformerFactory.newInstance().newTransformer().transform(
                    new StreamSource(new StringReader(xml)), dom);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return ((Document)dom.getNode()).getDocumentElement();
    }

}
