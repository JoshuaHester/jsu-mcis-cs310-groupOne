package edu.jsu.mcis;

import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class LoadXML extends ArgumentParser{
	private File xmlFile; 
/*	
	private ArrayList<String> positionalArgList;
	private ArrayList<String> optionalArgList;
	private Hashtable<String,Argument> argumentTable;
//*/	
	public LoadXML(String fileName){
		
		positionalArgList = new ArrayList<String>(5);
		optionalArgList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,Argument>(5);
	
		xmlFile = new File(fileName);
		 
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc; 
		try{
			dbFactory = DocumentBuilderFactory.newInstance();			
			dBuilder= dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);//throw cannot find file, cannot parse file 
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			
			NodeList nList = doc.getElementsByTagName("Argument");
			
			for (int i = 0; i < nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					String argName; 
					String dataType="String";
					String desc ="";
					String defaultVal=null;
					argName = eElement.getElementsByTagName("Name").item(0).getTextContent();
					try{
						dataType = eElement.getElementsByTagName("Type").item(0).getTextContent();
					}catch(Exception e){}
					Argument.DataType type;
					switch(dataType){
					case "int":
						type = Argument.DataType.INT;
						break;						
					case "float":
						type = Argument.DataType.FLOAT;
						break;
					case "boolean":
						type = Argument.DataType.BOOLEAN;
						defaultVal = "false";
						break;
					case "String":
					default:
						type = Argument.DataType.STRING;
					}
					argumentTable.put(argName, new Argument(type,argName));
					try{
						eElement.getElementsByTagName("Optional").item(0).getTextContent();
						optionalArgList.add(argName);
					}catch(Exception e){
						positionalArgList.add(argName);
					}
					try{
						desc = eElement.getElementsByTagName("Description").item(0).getTextContent();
						argumentTable.get(argName).setDescription(desc);
					}catch(Exception e){}
					try{
						defaultVal = eElement.getElementsByTagName("Default").item(0).getTextContent();
						argumentTable.get(argName).setValue(defaultVal);
					}catch(Exception e){}
				}
			}
		
		}catch (IOException ex) {
			System.out.println("Path : " + xmlFile.getAbsolutePath());
		}catch (SAXException ex) {
			System.out.println("dog.................p[[u[u...");
		}catch (ParserConfigurationException ex) {
			System.out.println("truck'''''''''''''");
		}
	}
		
	public List getPosArgs(){
		return positionalArgList;
	}
	
	public List getOptArgs(){
		return optionalArgList;
	}
	
	public Hashtable getArgs(){
		return argumentTable;
	}
	
}
