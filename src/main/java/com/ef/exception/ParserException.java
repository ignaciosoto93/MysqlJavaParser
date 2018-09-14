package com.ef.exception;

import com.ef.BaseRuntimeException;

public class ParserException extends BaseRuntimeException {

	private ParserError error;
	private String description;

	public ParserException(ParserError error, String message, Throwable cause) {
		super(message, cause);
		this.error = error;
		this.description = message;
	}

	public ParserException(ParserError error, String message) {
		this(error, message, null);
	}

	@Override
	public Integer getCode() {
		return error.getCode();
	}

	@Override
	public String getMessage() {
		return error.getMessage();
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getDiscriminator() {
		return "P";
	}

}
