package com.gssamerica.mdm.services.exceptions;

public abstract class MDMException extends Exception{
	
	public static final String ID = "GSSMDM00000"; 
	
	@Override
	public String getMessage() {
		String msg = null;
		msg = super.getMessage();
		return (getMDMErrorMessage()+" "+msg);
	}
	
	public abstract String getMDMErrorMessage();

}
