package com.natixistest.playlist.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -880796114960219491L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
