package org.caeit.cs.api.security;

public class SecurityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8743353649187351507L;

	public SecurityException() {
		super();
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}
}