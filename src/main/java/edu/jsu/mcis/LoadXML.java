package edu.jsu.mcis;

import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import org.xml.sax.SAXException;

public class LoadXML{
	private File xmlFile; 
	
	private ArrayList<String> positionalArgList;
	private ArrayList<String> optionalArgList;
	private Hashtable<String,Argument> argumentTable;
	
	public LoadXML(String fileName){
		
		positionalArgList = new ArrayList<String>(5);
		optionalArgList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,Argument>(5);
	
		//entire thing must be in bolck to comile. an exception is being thrown, trying to debug
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
				System.out.println("car");
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
		
		}catch(Exception e){e.getCause();}
	}
	
	public Argument getArgument(String argName){
//		if(argumentTable.containsKey(argName)){
			return argumentTable.get(argName);
/*		}
		else{	
			Argument val = new Argument("");
			val.setValue("");
			return val;
		}*/
	}
	
	public int getNumArgs(){
		return argumentTable.size();
	}
	
	public int getNumPosArguments(){
		return positionalArgList.size();
	}
	
	public int getNumOptArguments(){
		return optionalArgList.size();
	}
	
	public void translateDataUp(List posList, List optList, Hashtable args){
		posList=(ArrayList) positionalArgList.clone();
		optList=(ArrayList) optionalArgList.clone();
		args=(Hashtable) argumentTable.clone();
	}
	
	public ArrayList getPosArgs(){
		return positionalArgList;
	}
	
	public ArrayList getOptArgs(){
		return optionalArgList;
	}
	
	public Hashtable getArgs(){
		return argumentTable;
	}
	
}
