package com.ubs.datareader;

/**
 * Generic API definition for a Reader.
 */
public interface Reader
{
	
	/**
	 * Read and extract data for the given input source 
	 * and bind it to generic type T.
	 * 
	 * @return T
	 * 
	 * @throws ReaderException
	 */
	public <T> T read() throws ReaderException;
}
