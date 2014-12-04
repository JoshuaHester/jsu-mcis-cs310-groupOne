package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLTools {
	public static ArgumentParser p;
	public static ArrayList<String> argNameList;
	
	public static Argument a;
	
	public static ArgumentParser loadParser(String fileName) {
		p = new ArgumentParser();
	
		File xmlFile = new File(fileName);
		 
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc; 
		try{
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			
			NodeList nList = doc.getElementsByTagName("Argument");
			
			for (int i = 0; i < nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					
					String argName = ""; 
					String dataType = "String";
					String desc = "";
					String defaultVal = null;
					boolean optional = false;
					String shortName = null;
					
					try{
						argName = eElement.getElementsByTagName("Name").item(0).getTextContent();
					}catch(Exception e){throw new XMLException(fileName, "The following tag is missing: [Name].");}
					
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
					}catch(Exception e){if(optional&&type!=Argument.DataType.BOOLEAN){ throw new XMLException(fileName, "The following tag is missing: [Default].");}}
					
					if(optional){
						if(dataType.equals("boolean")){
							p.addFlag(argName);
						}
						else{
							p.addOptionalArgument(argName, type, defaultVal);
						}												
					}
					else {
						p.addArgument(argName, type);
					}
					
					try{
						desc = eElement.getElementsByTagName("Description").item(0).getTextContent();
						p.getArgument(argName).setDescription(desc);
					}catch(Exception e){}
					
					try{
						shortName = eElement.getElementsByTagName("Short").item(0).getTextContent();
					}catch(Exception e){}
					
					if(optional){
						if(shortName != null){
							p.getArgument(argName).setShortName(shortName);
						}
					}
				}
			}
			return p;
		
		}catch (IOException ex) {throw new XMLException(fileName, "The XML file cannot be found.");
		}catch (SAXException ex) {throw new XMLException(fileName, "The XML file is built incorrectly.");
		}catch (ParserConfigurationException ex) {throw new XMLException(fileName, "Critical error, please notify the developers [ParserConfigurationException].");
		}
	}
		
		
	public static void saveParser(ArgumentParser argumentP, String filename){
		p = argumentP;
		BufferedWriter writer;
		String s = "<?xml version=\"1.0\"?>\n<Arguments>\n";
		
		List positionalArgList = p.getPositionalArgs();
		int positionalArgAmt = p.getNumPosArguments();
		for(int i = 0; i<positionalArgAmt; i++){
			s += "\t<Argument>\n";
			s += "\t\t<Name> " + p.argumentTable.get(positionalArgList.get(i)).getName() + " </Name>\n";
			s += "\t\t<Type> " + p.argumentTable.get(positionalArgList.get(i)).getType() + " </Type>\n";
			s += "\t\t<Description> " + p.argumentTable.get(positionalArgList.get(i)).getDescription() + " </Description>\n";
			s += "\t</Argument>\n";
		}
		
		List optionalArgList = p.getOptionalArgs();
		int optionalArgAmt = p.getNumOptArguments();
		for(int i = 0; i<optionalArgAmt; i++){
			s += "\t<Argument>\n";
			s += "\t\t<Name> " + p.argumentTable.get(optionalArgList.get(i)).getName() + " </Name>\n";
			s += "\t\t<Type> " + p.argumentTable.get(optionalArgList.get(i)).getType() + " </Type>\n";
			s += "\t\t<Optional />\n";
			s += "\t\t<Description> " + p.argumentTable.get(optionalArgList.get(i)).getDescription() + " </Description>\n";
			s += "\t\t<Default> " + p.argumentTable.get(optionalArgList.get(i)).getValue() + "</Default>\n";
			s += "\t</Argument>\n";
		}
		s += "</Arguments>";
		System.out.println(s);
		
		try{
			File file = new File(filename);
			file.createNewFile();
			
			writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(s);
			writer.flush();
			writer.close();
		}catch(FileNotFoundException e){
			System.out.println("File Not Found");
			System.exit(1);
		}catch(IOException e){
			System.out.println("Input/Output Exception");
			System.exit(1);
		}
	}
}
