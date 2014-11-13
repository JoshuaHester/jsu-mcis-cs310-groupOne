package edu.jsu.mcis;

public class XMLException extends RuntimeException{
	private String fileName;
	private String message;
	private String exceptionClassInfo;
	
	public XMLException(String fileName, String message){
		this.fileName=fileName;
		this.message=message;
	}
	
	public String getFile(){
		return fileName;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String toString(){
		
		return exceptionClassInfo + "An error has occured: " + message;
		
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}