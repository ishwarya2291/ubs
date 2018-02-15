package com.ubs.datareader;
/**
 * Generic API definition for a Processor.
 */
public interface Processor<X, Y>
{
	/**
	 * 
	 * Process input of generic type Y and return processed output
	 * of generic type X.
	 * 
	 * @param input of type Y
	 * 
	 * @return - process and return processed output of type X.
	 * 
	 * @throws ProcessorException
	 */
	public X process(Y input) throws ProcessorException;
}
