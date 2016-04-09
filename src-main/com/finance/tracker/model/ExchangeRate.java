package com.finance.tracker.model;

import java.util.List;
import java.util.Map;

public class ExchangeRate {

	private double rate;
	private Currency base;
	private Currency toGet;
	private Map<Currency, Double> manyResults;

	public Currency getBase() {
		return base;
	}

	public void setBase(Currency base) {
		this.base = base;
	}

	public Currency getToGet() {
		return toGet;
	}

	public void setToGet(Currency toGet) {
		this.toGet = toGet;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Map<Currency, Double> getManyResults() {
		return manyResults;
	}

	public void setManyResults(Map<Currency, Double> manyResults) {
		this.manyResults = manyResults;
	}
	
	

}
