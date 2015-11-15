package com.humbinal.xml;

import java.util.*;
import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4jXml {
	
	
	
	public static void main(String[] args) throws Exception {		
		
		SAXReader reader = new SAXReader();
        Document document = reader.read("urls.xml");
        Element root = document.getRootElement();
        ArrayList arrayList=new ArrayList();
        String str[]=null;
        List<Node> list= document.selectNodes("//item");
        //遍历list
        
        for (Node node : list) {
			arrayList.add(node.getText());
		}
        
        for (int i = 0; i < arrayList.size(); i++) {
        	int index=arrayList.get(i).toString().lastIndexOf("/")+1;
			System.out.println(arrayList.get(i).toString().substring(index));		
		}        
        System.out.println(arrayList.size());
		//得到item的父节点
		//List <Node> list1= document.selectNodes("//item/parent::*");
		
		/*for (Node node : list1) {
			String s=node.getStringValue();
			System.out.println(s);
		}*/
		
	}	
}
