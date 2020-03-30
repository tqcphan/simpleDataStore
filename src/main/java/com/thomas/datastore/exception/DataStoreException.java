package com.thomas.datastore.exception;


	/**
	 * @author thomasphan
	 *
	 */
	public class DataStoreException extends RuntimeException{
		
		   private static final long serialVersionUID = 1L;

		    /**
		     * @param message
		     */
		    public DataStoreException(String message) {
		        super(message);
		    }

		    /**
		     * @param message
		     * @param cause
		     */
		    public DataStoreException(String message, Throwable cause) {
		        super(message, cause);
		    }

	}

