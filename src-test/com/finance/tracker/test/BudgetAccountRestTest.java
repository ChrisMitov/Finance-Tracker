package com.finance.tracker.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.Budget;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.dao.BudgetDao;
import com.finance.tracker.model.dao.UserDAO;
import com.finance.tracker.view.model.BudgetByAccount;

public class BudgetAccountRestTest {
	
	@Test
	public void editAccounts() throws FinanceTrackerException{
		Budget b = new BudgetDao().foundById(5502);
		b.deleteAllAccounts();
		new BudgetDao().updateBudget(b);
		Account d = new Account("Nov", 20, new UserDAO().getUser(3051));
		b.addAccount(d);
		new BudgetDao().updateBudget(b);
		Collection<Account>accounts = b.getAllAccounts();
		System.out.println("After");
		for(Account acc:accounts){
			System.out.println(acc.getTitle());
		}
		
	}

	@Test
	public void test() {
		System.out.println("Test");
		Collection<IBudget> budgets = new UserDAO().getAllBudgetsByUser(3051);
		System.out.println("Budgets:");
		for(IBudget b:budgets){
			System.out.println(b.getTitle());
		}
		Collection<IAccount> accounts = (List<IAccount>) new UserDAO().getAllAccounts(3051);
		System.out.println("Accounts:");
		for(IAccount a:accounts){
			System.out.println(a.getTitle());
		}
		
		Map<IAccount, Integer> map = new HashMap<IAccount, Integer>();
		
		Collection<BudgetByAccount> makeJason = new HashSet<BudgetByAccount>();
		
		for (IBudget budget : budgets) {
			Collection<Account> accountsPerBudget = budget.getAllAccounts();
			for(Account a:accountsPerBudget){
				System.out.println(budget.getTitle()+"-"+a.getTitle()+"!");
			}
			for (IAccount a : accounts) {
				for (Account acc : accountsPerBudget) {
					if (acc.equals(a)) {
						if (!map.containsKey(acc)) {
							map.put(a, 1);
						} else {
							int number = map.get(acc);
							map.put(a, number + 1);
						}
					}
				}
			}
		}
		for (Iterator<IAccount> it = map.keySet().iterator(); it.hasNext();) {
			Account a = (Account) it.next();
			String title = a.getTitle();
			int times = map.get(a);
			System.out.println(title + "-"+times+" map");
			makeJason.add(new BudgetByAccount(title, times));
		}
		
		for(BudgetByAccount b:makeJason){
			System.out.println(b.getTimes()+" "+b.getAccount()+"?");
		}
	}

}
