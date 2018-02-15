package com.ubs.datareader.companydata;
/**
 * 
 * Sample implementation of processor.
 *
 * Processes employee records stored as list of String array
 * and outputs List<Employee> objects.
 */
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

import com.ubs.datareader.Processor;
import com.ubs.datareader.ProcessorException;
import com.ubs.model.CompanyInfo;
import com.ubs.model.ICompanyInfo;
import com.ubs.util.CurrencyConverter;

public class CompanyDataProcessor implements Processor<Map<String, BigDecimal>, List<String[]>>
{
	
	private static final Map<String, String> cityCountryMap = new HashMap<String, String>();
	
	static
	{
		cityCountryMap.put("London", "UK");
	}
	
	@Override
	public Map<String, BigDecimal> process(List<String[]> input) throws ProcessorException 
	{
		int lineNumber = 0;
		
		Map<String, List<BigDecimal>> tempMap = new HashMap<String, List<BigDecimal>>();
		
		for (String[] nextArray : input)
		{
			ICompanyInfo companyInfo = new CompanyInfo();
			lineNumber++;
			
			// Skip first line.
			if (lineNumber == 1)
				continue;
			companyInfo.setId(Integer.parseInt(nextArray[0]));
			companyInfo.setCode(Integer.parseInt(nextArray[1]));
			companyInfo.setCity(nextArray[2]);
			companyInfo.setCountry(nextArray[3]);
			companyInfo.setCreditRating(nextArray[4]);
			companyInfo.setCurrency(nextArray[5]);
			companyInfo.setAmount(Double.parseDouble(nextArray[6]));
			
			String city         = companyInfo.getCity();
			String country      = companyInfo.getCountry();
			String creditRating = companyInfo.getCreditRating();
			String currency     = companyInfo.getCurrency();
			Double amount       = companyInfo.getAmount();
			BigDecimal amountInEuro = CurrencyConverter.convertToEuro(amount, currency);
			
			if ("".equals(country.trim()))
			{
				// country is not specified
				// get the country for the city from the city-country mapping.
				country = cityCountryMap.get(city);
			}
			
			if (tempMap.containsKey(country + "|" + creditRating))
			{
				List<BigDecimal> listBigDecimal = tempMap.get(country + "|" + creditRating);
				listBigDecimal.add(amountInEuro);
			}
			else
			{
				List<BigDecimal> listBigDecimal = new ArrayList<BigDecimal>();
				listBigDecimal.add(amountInEuro);
				
				tempMap.put(country + "|" + creditRating, listBigDecimal);
			}
		}
		
		// Compute the average amount in Euro grouped by country and credit rating
		// as dictated by the requirement.
		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		Iterator<String> keyIterator = tempMap.keySet().iterator();
		while (keyIterator.hasNext())
		{
			String nextKey = keyIterator.next();
			
			List<BigDecimal> listBigDecimal = tempMap.get(nextKey);
			
			BigDecimal noOfItems = new BigDecimal(listBigDecimal.size());
			
			BigDecimal total = BigDecimal.ZERO;
			
			for (BigDecimal nextItem : listBigDecimal)
			{
				total = total.add(nextItem); 
			}
			
			BigDecimal avgAmountInEuro = total.divide(noOfItems, 2, RoundingMode.HALF_UP);
			
			resultMap.put(nextKey, avgAmountInEuro);
			keyIterator.remove();
		}
		
		return resultMap;
	}
}
