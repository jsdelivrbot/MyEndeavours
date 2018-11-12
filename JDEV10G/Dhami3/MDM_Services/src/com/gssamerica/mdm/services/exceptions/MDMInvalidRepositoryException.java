package com.gssamerica.mdm.services.exceptions;

public class MDMInvalidRepositoryException extends MDMException {

	private String MDMErrorMsg = null;
	public static final String ID = "GSSMDM00007"; 
	
	public MDMInvalidRepositoryException(String errMsg){
		this.MDMErrorMsg = errMsg;
	}
	
	@Override
	public String getMDMErrorMessage() {
		return this.MDMErrorMsg; 
	}

}
