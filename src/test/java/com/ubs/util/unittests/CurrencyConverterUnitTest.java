package com.ubs.util.unittests;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.math.MathContext;
import com.ubs.util.CurrencyConverter;

public class CurrencyConverterUnitTest {

	@Test
	public void convertToEuroUSDToEURTest()
	{
		//Arrange
		Double expectedAmount = 100.0;
		String expectedCurrency = "USD";
		BigDecimal expectedResult = new BigDecimal((1 / 1.35) * expectedAmount,  MathContext.DECIMAL64);
				
		//Act
		BigDecimal actualResult = CurrencyConverter.convertToEuro(expectedAmount, expectedCurrency);
		
		//Assert
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	@Test
	public void convertToEuroGBPToEURTest()
	{
		//Arrange
		Double expectedAmount = 20.0;
		String expectedCurrency = "GBP";
		BigDecimal expectedResult = new BigDecimal(1.654 * (1 / 1.35) * expectedAmount,  MathContext.DECIMAL64);

		//Act
		BigDecimal actualResult = CurrencyConverter.convertToEuro(expectedAmount, expectedCurrency);

		//Assert
		assertTrue(actualResult.compareTo(expectedResult) == 0);
	}
	
	@Test 
	public void convertToEuroCHFToEURTest()
	{
		//Arrange
		Double expectedAmount = 100.0;
		String expectedCurrency = "CHF";
		BigDecimal expectedResult = new BigDecimal(1.1 * (1 / 1.35) * expectedAmount,  MathContext.DECIMAL64);
		
		//Act
		BigDecimal actualResult = CurrencyConverter.convertToEuro(expectedAmount, expectedCurrency);
		
		//Assert
		assertTrue(actualResult.compareTo(expectedResult) == 0);
	}

}