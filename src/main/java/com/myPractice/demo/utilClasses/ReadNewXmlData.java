package com.myPractice.demo.utilClasses;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Read XMl Data
 * 
 * <P>
 * Has methods to read xml test data
 * <P>
 * 
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class ReadNewXmlData {
	
	public String filepath;
	
	public void ReadNewXmlData(String fp) { 
		
		this.filepath = fp;
	}

	public Document buildDoc(String filePath) {

		File fXmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return doc;
	}

	public String getRootNodeName(String filePath) {

		Document doc = buildDoc(filePath);
		doc.getDocumentElement().normalize();

		return doc.getDocumentElement().getNodeName();
	}

	public String getData(Document doc, String tagName) {

		return doc.getElementsByTagName(tagName).toString();
	}

}