package com.wallethub.exception;

import com.wallethub.BaseRuntimeException;

public class JavaMysqlParserException extends BaseRuntimeException {

	private JavaMysqlParserError error;
	private String description;

	public JavaMysqlParserException(JavaMysqlParserError error, String message, Throwable cause) {
		super(message, cause);
		this.error = error;
		this.description = message;
	}

	public JavaMysqlParserException(JavaMysqlParserError error, String message) {
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
