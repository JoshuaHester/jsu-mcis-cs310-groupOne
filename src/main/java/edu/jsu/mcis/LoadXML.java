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
	
	private List<String> positionalArgList;
	private List<String> optionalArgList;
	private Hashtable<String,Argument> argumentTable;
	
	public LoadXML(String fileName){
		
		positionalArgList = new ArrayList<String>(5);
		optionalArgList = new ArrayList<String>(5);
		argumentTable = new Hashtable<String,Argument>(5);
	
		try{//entire thing must be in bolck to comile. an exception is being thrown, trying to debug
			xmlFile = new File(fileName);
		 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize(); //? look up what this is later
			doc.getDocumentElement().getNodeName();
			NodeList nList = doc.getElementsByTagName("Argument");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
 
				Node nNode = nList.item(temp);
			 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
					Element eElement = (Element) nNode;
					
					String argName = eElement.getAttribute("Name");
					
					String dataType = eElement.getElementsByTagName("Type").item(0).getTextContent();
					String desc = eElement.getElementsByTagName("Description").item(0).getTextContent();
					String defaultVal = eElement.getElementsByTagName("Default").item(0).getTextContent();
					argumentTable.put(argName, new Argument(argName));
					Argument.DataType type = Argument.DataType.STRING;
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
					argumentTable.get(argName).setValue(defaultVal);
					argumentTable.get(argName).setDescription(desc);
					
					
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
	
	public int getnumargs(){
		return argumentTable.size();
	}
	
}
