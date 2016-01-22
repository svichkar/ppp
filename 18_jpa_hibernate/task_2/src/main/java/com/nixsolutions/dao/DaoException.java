package com.nixsolutions.dao;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DaoException(String string) {
		super(string);
	}

	public DaoException(String string, Exception e) {
		super(string, e);
	}

}
