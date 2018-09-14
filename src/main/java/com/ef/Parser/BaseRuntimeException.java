package com.ef.Parser;

public abstract class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 8602996614160338831L;

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract Integer getCode();

	public abstract String getMessage();

	public abstract String getDescription();

	public abstract String getDiscriminator();

	@Override
	public String toString() {
		return getCode() + ": " + super.toString();
	}
}

