package edu.jsu.mcis;

//If this error ever is thrown, something has gone horribly HORRIBLY wrong....
public class CriticalErrorException extends RuntimeException{
	private String exceptionClassInfo;
	private String cause;
	
	public CriticalErrorException(String cause){
		this.cause = cause;
	}
	
	public String toString(){
		return exceptionClassInfo +" WARNING: The ArgumentParser program has encountered a critical error. Please contact the developers and let them know of this error. This error should never be thrown. " + cause;
	}
	
	private void setExceptionClassName(){
		exceptionClassInfo = this.getClass().getSimpleName();
	}
}
