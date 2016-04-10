package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.finance.tracker.model.Currency;
import com.finance.tracker.model.ExchangeRate;
import com.finance.tracker.model.ExchangeRateConventor;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.CurrencyDAO;
import com.finance.tracker.model.dao.UserDAO;

public class ExchangeRateTest {


	@Test
	public void test() throws Exception {
		System.out.println("Test 1");
		new CurrencyDAO().getRate(Currency.EUR, Currency.BGN);

	}

	@Test
	public void test2() throws Exception {
		System.out.println("Test 2");
		List<Currency> toConvert = new ArrayList<Currency>();
		toConvert.add(Currency.EUR);
		toConvert.add(Currency.USD);
		new CurrencyDAO().getManyRates(toConvert, Currency.BGN);
	}

	@Test
	public void test3() throws Exception {
		System.out.println("Test 3");
		IUser user = new UserDAO().getUser(3051);
		Currency newCurrency = Currency.EUR;
		ExchangeRate e = new CurrencyDAO().getRate(newCurrency, user.getCurrency());
		System.out.println(e.getRate() + "!!!");
	}

	@Test
	public void test4() throws Exception {
		System.out.println("Test 4");
		IUser user = new UserDAO().getUser(3051);
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(user.getUserId());
		Currency newCurrency = Currency.EUR;
		System.out.println("Before");
		for (IBudget b : budgets) {
			System.out.println(b.getTotalAmount());
		}
		ExchangeRateConventor conv = new ExchangeRateConventor(user, newCurrency);
		System.out.println(conv.getRate() + "@$#");
		conv.covert();
		System.out.println("After");
		for (IBudget b : budgets) {
			System.out.println(b.getTotalAmount());
		}
	}

	@Test
	public void test5() throws Exception {
		System.out.println("Test 5");
		IUser user = new UserDAO().getUser(3051);
		Currency newCurrency = Currency.EUR;
		ExchangeRate ex = new CurrencyDAO().getRate(newCurrency, user.getCurrency());
		System.out.println(ex.getRate() + "@$#");
	}

	@Test
	public void test6() throws Exception {
		System.out.println("Test 6");
		double result = new CurrencyDAO().convert(500, Currency.BGN, Currency.EUR);
		System.out.println(result);
	}

}
