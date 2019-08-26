package com.quintifi.Flyer.exceptions;

/**
 * 
 * @author nchopra
 *
 */
public enum ApiExCode implements ExceptionCodes {

	RECORD_NOT_FOUND("FY_404", "Record Not Found!"), NOT_VALID_RECORD("TD_303", "Not a valid record"),;

	private String code;
	private String message;

	ApiExCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
