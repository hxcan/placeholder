package com.simo.ugmate.stack.util;

public class SimoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimoException() {
    }

    public SimoException(String name) {
        super(name);
    }

    public SimoException(Exception cause) {
        super(cause);
    }
}
