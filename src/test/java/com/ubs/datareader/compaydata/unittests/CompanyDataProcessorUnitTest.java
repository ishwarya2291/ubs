package com.ubs.datareader.compaydata.unittests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ubs.datareader.companydata.CompanyDataProcessor;

public class CompanyDataProcessorUnitTest {

	@Test
	public void processTest() throws Exception {
		//Arrange
		String[] expectedItems1 = {"2317", "6399028", "Moscow", "RUS", "Aaa", "CHF", "112170814.9"};
		String[] expectedItems2 = {"2318", "8084107", "Bangalore", "IND", "Aaa+", "CHF", "836211889.9"};
		
		
		List<String[]> expectedInputData = new ArrayList<>();
		expectedInputData.add(expectedItems1);
		expectedInputData.add(expectedItems2);
		
		Map<String,BigDecimal> expectedResult = new HashMap<String, BigDecimal>();
		expectedResult.put("IND|Aaa+", new BigDecimal(681357836.21, MathContext.DECIMAL64));

		CompanyDataProcessor companyDataProcessor = new CompanyDataProcessor();
		//CompanyOperations companyOperations = new CompanyOperations();

		//Act
		Map<String, BigDecimal> actualResult = companyDataProcessor.process(expectedInputData); 
		
		//Assert
		assertTrue(actualResult.get("IND|Aaa+").compareTo(expectedResult.get("IND|Aaa+")) == 0);
	}

}
