package com.humbinal.xml;

import java.util.ArrayList;

/*import java.io.File;
import java.io.IOException;

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
import org.xml.sax.SAXException;*/

public class SunXml {

	/*public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// TODO Auto-generated method stub
		// 解析文件，生成document对象
		  DocumentBuilder builder = DocumentBuilderFactory.newInstance()
		    .newDocumentBuilder();
		  Document document = builder.parse(new File("urls.xml"));
		  // 生成XPath对象
		  XPath xpath = XPathFactory.newInstance().newXPath();
		  // 获取节点值
		  //String webTitle = (String) xpath.evaluate("//item", document,XPathConstants.STRING);
		  //System.out.println(webTitle);
		  
		// 获取节点集合
		  NodeList urls = (NodeList) xpath.evaluate("//item", document,XPathConstants.NODESET);
		  for (int i = 0; i < urls.getLength(); i++) {
		   Node url = urls.item(i);	
		   
		   int index=url.getTextContent().toString().lastIndexOf("/")+1;
			
		   System.out.println(url.getTextContent());
		   System.out.println(url.getTextContent().toString().substring(index));	
		   
		   
		   
		  }
		  System.out.println(urls.getLength());
		
	}*/
	
	public static void main(String[] args) throws Exception {
		UrlBuilder urlBuilder=new UrlBuilder();
		ArrayList arrayList= urlBuilder.getUrlList();
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
			System.out.println(arrayList.get(i).toString().substring(arrayList.get(i).toString().lastIndexOf("/")+1));			
		}
		System.out.println(arrayList.size());
	}

}
