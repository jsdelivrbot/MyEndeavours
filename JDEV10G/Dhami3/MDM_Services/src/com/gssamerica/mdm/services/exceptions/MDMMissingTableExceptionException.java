package com.gssamerica.mdm.services.exceptions;

public class MDMMissingTableExceptionException extends MDMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MDMErrorMsg = null;
	public static final String ID = "GSSMDM00004"; 
	
	public MDMMissingTableExceptionException(String errMsg){
		this.MDMErrorMsg = errMsg;
	}
	
	public String getMDMErrorMessage() {
		return this.MDMErrorMsg; 
	}

}
