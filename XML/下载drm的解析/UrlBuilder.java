package com.humbinal.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UrlBuilder {
	public ArrayList getUrlList() throws  ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		
		ArrayList arrayList=new ArrayList();
		// 解析文件，生成document对象
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = builder.parse(new File("urls.xml"));
		// 生成XPath对象
		XPath xpath = XPathFactory.newInstance().newXPath();
		// 获取节点集合
		NodeList urls = (NodeList) xpath.evaluate("//item", document,
				XPathConstants.NODESET);
		for (int i = 0; i < urls.getLength(); i++) {
			Node url = urls.item(i);
			arrayList.add(url.getTextContent());			
		}		
		return arrayList;

	}
}
