package org.jewel.util.xml;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jewel.JewelException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class XMLUtil {

	public static DocumentBuilderFactory newDocumentBuilderFactory() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		return factory;
	}

	public static DocumentBuilder newDocumentBuilder() {
		DocumentBuilderFactory factory = newDocumentBuilderFactory();
		try {
			return factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new JewelException("Failed to create DocumentBuilder.", e);
		}
	}

	public static Document parse(Path path) {
		DocumentBuilder builder = newDocumentBuilder();
		try {
			return builder.parse(path.toFile());
		} catch (SAXException | IOException e) {
			String message = String.format("Failed to read XML file %s.", path);
			throw new JewelException(message, e);
		}
	}

	public static SAXParserFactory newSAXParserFactory() {
		return SAXParserFactory.newInstance();
	}

	public static SAXParser newSAXParser() {
		SAXParserFactory factory = newSAXParserFactory();
		try {
			return factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			throw new JewelException("Failed to create SAXParser.", e);
		} catch (SAXException e) {
			throw new JewelException("Failed to create SAXParser.", e);
		}
	}

	public static void parse(Path path, DefaultHandler handler) {
		SAXParser parser = newSAXParser();
		try {
			parser.parse(path.toFile(), handler);
		} catch (SAXException | IOException e) {
			String message = String.format("Failed to read XML file %s.", path);
			throw new JewelException(message, e);
		}
	}

	public static TransformerFactory newTransformerFactory() {
		return TransformerFactory.newInstance();
	}

	public static Transformer newTransformer() {
		TransformerFactory factory = newTransformerFactory();
		try {
			return factory.newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new JewelException("Failed to create Transformer.", e);
		}
	}

	public static void save(Document document, Path path) {
		DOMSource source = new DOMSource(document.getDocumentElement());
		StreamResult result = new StreamResult(path.toFile());
		Transformer transformer = newTransformer();
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			String message = String.format("Failed to save XML file %s.", path);
			throw new JewelException(message, e);
		}
	}

	public static XPathFactory newXPathFactory() {
		return XPathFactory.newInstance();
	}

	public static XPath newXPath() {
		XPathFactory factory = newXPathFactory();
		return factory.newXPath();
	}

	public static String query(String xPathExpression, Node node, XPath xPath) {
		try {
			return xPath.evaluate(xPathExpression, node);
		} catch (XPathExpressionException e) {
			String message = String.format(
					"Failed to evaluate Node %s with xPath expression %s.",
					node.getLocalName(), xPathExpression);
			throw new JewelException(message, e);
		}
	}

	public static NodeList queryList(String xPathExpression, Node node,
			XPath xPath) {
		try {
			return (NodeList) xPath.evaluate(xPathExpression, node,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			String message = String.format(
					"Failed to evaluate Node %s with xPath expression %s.",
					node.getLocalName(), xPathExpression);
			throw new JewelException(message, e);
		}
	}

}
