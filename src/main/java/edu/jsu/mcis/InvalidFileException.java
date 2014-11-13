package edu.jsu.mcis;

public class InvalidFileException extends RuntimeException{
	private String fileName;
	private String exceptionClassInfo;
	
	public InvalidFileException(String fileName){

		this.fileName=fileName;
		
	}
	
	public String getFile(){
		return fileName;
	}
	
	
	public String toString(){
		
		return exceptionClassInfo +" " + fileName + " is invalid, please fix the file";
		
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}
