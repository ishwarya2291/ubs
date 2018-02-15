package com.ubs.driver;
/**
 * Sample test to demonstrate how the DataExtractor
 * Reader and Processor work together.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import com.ubs.datareader.DataExtractor;
import com.ubs.datareader.ProcessorException;
import com.ubs.datareader.Reader;
import com.ubs.datareader.ReaderException;
import com.ubs.datareader.ReaderFactory;
import com.ubs.datareader.ReaderFactory.SupportedInputSrcTypes;
import com.ubs.datareader.companydata.CompanyDataProcessor;

public class UbsDriver 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		try
		{
			Map<String, String> initArgs = new HashMap<String, String>();
			initArgs.put("fullPathToDelimitedFile", new File( "." ).getCanonicalPath() + "/src/test/resources/" + "FILE.DAT");
			initArgs.put("fileDelimitedBy",   "\t");
			
			// Initialize the Delimited File reader from ReaderFactory.
			Reader companyDataDelimitedFileReader = ReaderFactory.getInstance().getReader(SupportedInputSrcTypes.DELIMITED_FILE_READER, initArgs);
			
			// Initialize the Processor.
			CompanyDataProcessor companyDataProcessor = new CompanyDataProcessor();
			
			// Set the reader and processor for the extractor.
			DataExtractor dataExtractor = new DataExtractor();
			dataExtractor.setReader(companyDataDelimitedFileReader);
			dataExtractor.setProcessor(companyDataProcessor);
			
			List<String[]> input = (List<String[]>) dataExtractor.getReader().read();
			
			Map<String, BigDecimal> result = (Map<String, BigDecimal>) dataExtractor.getProcessor().process(input);
			
			for (String nextKey : result.keySet())
			{
				System.out.println(nextKey + "=" + result.get(nextKey));
			}
		}
		catch(ReaderException ex)
		{
			System.out.println("Encountered exception -> " + ex.getMessage());
		}
		catch(ProcessorException ex)
		{
			System.out.println("Encountered exception -> " + ex.getMessage());
		}
		catch(IOException ex)
		{
			System.out.println("Encountered exception -> " + ex.getMessage());
		}
	}
}
