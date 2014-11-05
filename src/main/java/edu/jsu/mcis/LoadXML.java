package edu.jsu.mcis;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.util.*;

public class LoadXML{
	private File xmlFile; 
	
	private List<String> positionalArgList;
	private List<String> optionalArgList;
	private Hashmap<String,ArgumentValues> argumentTable;
	
	public LoadXML(String fileName,,,){
		try{
			xmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize(); //? look up what this is later
			
			NodeList nList = doc.getElementsByTagName("argument");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
 
				Node nNode = nList.item(temp);
		 
			//	System.out.println("\nCurrent Element :" + nNode.getNodeName());
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					eElement.getAttribute("name").toString;
					
					/*
					System.out.println("Staff id : " + eElement.getAttribute("id"));
					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
		 */
				}
			}
			
		
		
		}catch(Exception e){}
	}
	
	
	
	
}
