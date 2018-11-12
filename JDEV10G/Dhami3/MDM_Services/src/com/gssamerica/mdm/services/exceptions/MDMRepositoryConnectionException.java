package com.gssamerica.mdm.services.exceptions;

public class MDMRepositoryConnectionException extends MDMException {

	private String MDMErrorMsg = null;
	public static final String ID = "GSSMDM00006"; 
	
	public MDMRepositoryConnectionException(String errMsg){
		this.MDMErrorMsg = errMsg;
	}
	
	@Override
	public String getMDMErrorMessage() {
		return this.MDMErrorMsg; 
	}

}
