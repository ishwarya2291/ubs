package com.ubs.datareader;
/**
 * Sample implementation for a Delimited file reader.
 * 
 * Contains default implementation for loading a CSV file into 
 * memory as a list of String array. 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class DelimitedFileReader implements Reader
{
	private String fullPathToDelimitedFile;
	
	private String fileDelimitedBy;

	/**
	 * @return the fileDelimitedBy
	 */
	public String getFileDelimitedBy() 
	{
		return fileDelimitedBy;
	}

	/**
	 * @param fileDelimitedBy the fileDelimitedBy to set
	 */
	public void setFileDelimitedBy(String fileDelimitedBy) 
	{
		this.fileDelimitedBy = fileDelimitedBy;
	}

	/**
	 * @return fullPathToDelimitedFile
	 */
	public String getFullPathToDelimitedFile() 
	{
		return fullPathToDelimitedFile;
	}

	/**
	 * @param fullPathToDelimitedFile
	 */
	public void setFullPathToDelimitedFile(String fullPathToDelimitedFile) 
	{
		this.fullPathToDelimitedFile = fullPathToDelimitedFile;
	}
	
	/**
	 * 
	 * @param initArgs 
	 * 
	 * @throws ReaderException
	 */
	public DelimitedFileReader(Map<String, String> initArgs) throws ReaderException
	{
		String fullPathToDelimitedFile = initArgs.get("fullPathToDelimitedFile");
		
		if (fullPathToDelimitedFile == null)
		{
			StringBuilder errorMsg = new StringBuilder("Unable to create CSVReader.").append(" Missing required InitArg 'fullPathToDelimitedFile'.");
			throw new ReaderException(errorMsg.toString());
		}
		
		String fileDelimitedBy = initArgs.get("fileDelimitedBy");
		
		if (fileDelimitedBy == null)
		{
			StringBuilder errorMsg = new StringBuilder("Unable to create DelimitedFileReader.").append(" Missing required InitArg 'fileDelimitedBy'.");
			throw new ReaderException(errorMsg.toString());
		}
		
		this.fullPathToDelimitedFile = fullPathToDelimitedFile;
		this.fileDelimitedBy = fileDelimitedBy;
	}
	
	/**
	 * 
	 * Load the contents of the CSV file into memory as a List of String arrays.
	 * 
	 * @param fullPathToDelimitedFile
	 * 
	 * @return List<String[]>
	 * 
	 * @throws ReaderException on failure
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> read() throws ReaderException
	{
		List<String[]> input = new ArrayList<String[]>();
		BufferedReader bufferedReader = null;
		String nextLine = "";
		
		try 
		{
			bufferedReader = new BufferedReader(new FileReader(fullPathToDelimitedFile));
			while ((nextLine = bufferedReader.readLine()) != null) 
			{
				String[] nextLineAsArray = nextLine.split(fileDelimitedBy);
				input.add(nextLineAsArray);
			}
			
			return input;
		} 
		catch (FileNotFoundException ex) 
		{
			throw new ReaderException(ex);
		} 
		catch (IOException ex) 
		{
			throw new ReaderException(ex);
		} 
		finally 
		{
			if (bufferedReader != null) 
			{
				try 
				{
					bufferedReader.close();
				} 
				catch (IOException ex) 
				{
					throw new ReaderException(ex);
				}
			}
		}
	}
}
