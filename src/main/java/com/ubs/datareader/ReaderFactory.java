package com.ubs.datareader;
/**
 * Singleton instance of ReaderFactory that dispenses Readers based on configured reader types.
 */
import java.util.Map;

public class ReaderFactory
{
	public enum SupportedInputSrcTypes
	{
		DELIMITED_FILE_READER;
	}
	
	private static ReaderFactory theInstance = null;
	
	private ReaderFactory()
	{
		
    }
	
	public static ReaderFactory getInstance()
	{
		if (theInstance == null)
		{
			theInstance = new ReaderFactory();
		}
		
		return theInstance;
	}
	
	public Reader getReader(SupportedInputSrcTypes inputSrcType, Map<String, String> initArgs) throws ReaderException
	{
		//TODO: Drive this through configuration.
		if (SupportedInputSrcTypes.DELIMITED_FILE_READER.equals(inputSrcType))
		{
			return new DelimitedFileReader(initArgs);
		}
		
		StringBuilder errorMsg = new StringBuilder("DataReader for ").append(inputSrcType).append(" is not supported yet.");
		throw new ReaderException(errorMsg.toString());
	}
}

