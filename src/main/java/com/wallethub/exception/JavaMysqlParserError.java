package com.wallethub.exception;

public enum JavaMysqlParserError {
	UNKNOWN(1, "Unknow Error"), NOT_VALID_ARGUMENTS(2, "Please, check the provided arguments"),
	INVALID_PATH(3, "Invalid Path, please check the path file"),
	WRONG_LINE_FORMAT(4, "Wrong log Format, please check the delimiters of the log File Line");

	private final Integer code;
	private String message;

	JavaMysqlParserError(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
