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
			doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			
			NodeList nList = doc.getElementsByTagName("Argument");
			
			for (int i = 0; i < nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					String argName = ""; 
					String dataType="String";
					String desc ="";
					String defaultVal=null;
					boolean optional = false;
					String shortName = null;
					try{
						argName = eElement.getElementsByTagName("Name").item(0).getTextContent();
					}catch(Exception e){throw new XMLException(fileName, "The following tag is missing: [Name]");}
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
					try{
						String temp = eElement.getElementsByTagName("Optional").item(0).getTextContent();
						optional = true;
						if(temp.equals("false")){
							optional = false;
						}
					}catch(Exception e){}
					try{
						defaultVal = eElement.getElementsByTagName("Default").item(0).getTextContent();
					}catch(Exception e){if(optional&&type!=Argument.DataType.BOOLEAN){ throw new XMLException(fileName, "The following tag is missing: [Default]");}}
					try{
						desc = eElement.getElementsByTagName("Description").item(0).getTextContent();
					}catch(Exception e){}
					try{
						shortName = eElement.getElementsByTagName("Short").item(0).getTextContent();
					}catch(Exception e){}
					
					if(optional){
						if(dataType.equals("boolean")){
							addFlag(argName);
						}else{
							addOptionalArgument(type,argName,defaultVal);
						}
						if(shortName != null){
							getArgument(argName).setShortName(shortName);
						}
					}else{
						addArgument(type,argName);
					}
					getArgument(argName).setDescription(desc);
				}
			}
		
		}catch (IOException ex) {throw new XMLException(fileName, "The XML file cannot be found");
		}catch (SAXException ex) {throw new XMLException(fileName, "The XML file is built incorrectly");
		}catch (ParserConfigurationException ex) {throw new XMLException(fileName, "Critical error, please notify the developers [ParserConfigurationException]");
		}
	}
		
	List getPosArgs(){
		return positionalArgList;
	}
	
	List getOptArgs(){
		return optionalArgList;
	}
	
	Hashtable getArgs(){
		return argumentTable;
	}
	
}
