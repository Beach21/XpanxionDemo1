package com.myPractice.demo.utilClasses;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class ReadXMLData {

	private Document doc;
	private String fileName;

	public ReadXMLData(String fileName) {
		this.fileName = fileName;
	}

	public String getData(String locator) {
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(fileName);
		} catch (DocumentException e) {
			// e.printStackTrace();
		}
		String data = doc.selectSingleNode("//" + locator.replace('.', '/')).getText();
		return data;
	}
	
}
