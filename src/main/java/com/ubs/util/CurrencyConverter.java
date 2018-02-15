package com.ubs.util;

import java.math.BigDecimal;

public class CurrencyConverter{

	public static final Double CONVERSION_RATE_USD_TO_GBP = 1.654;
	public static final Double CONVERSION_RATE_CHF_TO_USD = 1.10;
	public static final Double CONVERSION_RATE_EUR_TO_USD = 1.35;
	
	public static BigDecimal convertToEuro(double amount, String currency) {
		
		if(!currency.equals("EUR")) {
			
			if(currency.equals("USD")) {
				
				return BigDecimal.valueOf(amount / CONVERSION_RATE_EUR_TO_USD);
				
			} else if(currency.equals("CHF")) {
				
				return BigDecimal.valueOf((amount / CONVERSION_RATE_EUR_TO_USD) * CONVERSION_RATE_CHF_TO_USD); 
				
			} else if(currency.equals("GBP")) {								
				
				return BigDecimal.valueOf((amount / CONVERSION_RATE_EUR_TO_USD) * CONVERSION_RATE_USD_TO_GBP); 
				
			}
			
		}
		
		return BigDecimal.valueOf(amount);
		
	}

}
