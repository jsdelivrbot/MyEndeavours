package com.gssamerica.mdm.services.exceptions;

public class MDMDatabaseException extends MDMException {

	private String MDMErrorMsg = null;
	public static final String ID = "GSSMDM00001"; 
	
	public MDMDatabaseException(String errMsg){
		this.MDMErrorMsg = errMsg;
	}
	
	@Override
	public String getMDMErrorMessage() {
		return this.MDMErrorMsg; 
	}

}
