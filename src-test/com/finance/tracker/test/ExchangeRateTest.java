package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ExchangeRate;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.CurrencyDAO;
import com.finance.tracker.model.dao.UserDAO;

public class ExchangeRateTest {
//
//	@Test
//	public void test() throws Exception {
//		System.out.println("Test 1");
//		new CurrencyDAO().getRate(Currency.EUR, Currency.BGN);
//
//	}
//
//	@Test
//	public void test2() throws Exception {
//		List<Currency> toConvert = new ArrayList<Currency>();
//		toConvert.add(Currency.EUR);
//		toConvert.add(Currency.USD);
//		new CurrencyDAO().getManyRates(toConvert, Currency.BGN);
//	}

	@Test
	public void test3() throws Exception {
		IUser user = new UserDAO().getUser(1101);
//		Currency currency = Currency.BGN;
		Currency newCurrency  =Currency.EUR;
		ExchangeRate e = new CurrencyDAO().getRate(newCurrency,user.getCurrency());
		System.out.println(e.getRate()+"!!!");
	}

}
