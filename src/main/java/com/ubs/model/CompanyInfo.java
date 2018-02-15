package com.ubs.model;

public class CompanyInfo implements ICompanyInfo{
	int id;
	int code;
	String city;
	String country;
	String creditRating;
	String currency;
	Double amount;
	
	public CompanyInfo() {
		
	}
	
	public CompanyInfo(int id, int code, String city, String country, String creditRating, String currency, Double amount) {
		this.id = id;
		this.code = code;
		this.city = city;
		this.country = country;
		this.creditRating = creditRating;
		this.currency = currency;
		this.amount = amount;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getCreditRating() {
		return creditRating;
	}

	@Override
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	@Override
	public String getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public Double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
