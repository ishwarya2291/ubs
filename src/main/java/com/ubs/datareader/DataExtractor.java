package com.ubs.datareader;
/**
 * 
 * DataExtractor contains a Reader and a Processor
 *
 */
public class DataExtractor 
{
	private Reader reader;
	
	@SuppressWarnings("rawtypes")
	private Processor processor;

	/**
	 * @return Reader
	 */
	public Reader getReader() 
	{
		return reader;
	}

	/**
	 * @param reader
	 */
	public void setReader(Reader reader) 
	{
		this.reader = reader;
	}

	/**
	 * @return the processor
	 */
	@SuppressWarnings("rawtypes")
	public Processor getProcessor() 
	{
		return processor;
	}

	/**
	 * @param processor the processor to set
	 */
	public void setProcessor(@SuppressWarnings("rawtypes") Processor processor) 
	{
		this.processor = processor;
	}
}
