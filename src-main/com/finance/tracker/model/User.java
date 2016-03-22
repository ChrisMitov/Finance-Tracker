package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;

public class User implements IUser {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Currancy currancy;
	private LocalDate jointedDate;
	private List<IAccount> allAccounts;
	private List<IBudget> allBudgets;

	public User(String firstName, String lastName, String email, String password, Currancy currancy) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setCurrancy(currancy);
		this.jointedDate = LocalDate.now();
		allAccounts = new ArrayList<IAccount>();
		allBudgets = new ArrayList<IBudget>();
	}
	
	@Override
	public void convertCurruncy(Currancy newCurrancy) {
		this.currancy=newCurrancy;		
	}
	
	

	@Override
	public void addAcount(IAccount newAccount) throws FinanceTrackerException {
		if (newAccount != null) {
			synchronized (this.allAccounts) {
				this.allAccounts.add(newAccount);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public void removeAccount(IAccount accountToDelete) throws FinanceTrackerException {
		if (accountToDelete != null && this.allAccounts.contains(accountToDelete)) {
			synchronized (this.allAccounts) {
				this.allAccounts.remove(accountToDelete);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public IAccount getAccount(String accountTitle) throws FinanceTrackerException {
		if (accountTitle != null && accountTitle.length() > 0) {
			boolean isAccountFound = false;
			for (IAccount account : allAccounts) {
				if (account.getTitle().equals(accountTitle)) {
					isAccountFound = true;
					return account;
				}
			}
			if (isAccountFound == false) {
				throw new FinanceTrackerException();
			}
		}
		return null;
	}

	@Override
	public void addBudget(IBudget newBudget) throws FinanceTrackerException {
		if (newBudget != null) {
			synchronized (this.allBudgets) {
				this.allBudgets.add(newBudget);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public IBudget getBudget(String budgetTitle) throws FinanceTrackerException {
		if (budgetTitle != null && budgetTitle.length() > 0) {
			boolean isBudgetFound = false;
			for (IBudget budget : allBudgets) {
				if (budget.getTitle().equals(budgetTitle)) {
					isBudgetFound = true;
					return budget;
				}
			}
			if (isBudgetFound == false) {
				throw new FinanceTrackerException();
			}
		}
		return null;
	}

	@Override
	public void removeBudget(IBudget budgetToDelete) throws FinanceTrackerException {
		if (budgetToDelete != null && this.allBudgets.contains(budgetToDelete)) {
			synchronized (budgetToDelete) {
				this.allBudgets.remove(this.allBudgets);
			}
		} else {
			throw new FinanceTrackerException();
		}
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) {
		if (firstName != null && firstName.length() > 0)
			this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		if (lastName != null && lastName.length() > 0)
			this.lastName = lastName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		if (email != null && email.length() > 0)
			this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		if (password != null && isPasswordSecured(password)) {
			this.password = password;
		} else {
			try {
				throw new PasswordException();
			} catch (PasswordException e) {
				e.getMessage();
			}
		}
	}

	@Override
	public Currancy getCurrancy() {
		return currancy;
	}

	@Override
	public void setCurrancy(Currancy currancy) {
		this.currancy = currancy;
	}

	@Override
	public LocalDate getJointedDate() {
		return jointedDate;
	}

	private boolean isPasswordSecured(String password) {
		if (password != null && password.length() > 0) {
			boolean figure = false;
			boolean capitalLetter = false;
			boolean smallLetter = false;
			for (int index = 0; index < password.length(); index++) {
				char letter = password.charAt(index);
				if (letter >= '0' && letter <= '9') {
					figure = true;
				}
				if (letter >= 'a' && letter <= 'z') {
					smallLetter = true;
				}
				if (letter >= 'A' && letter <= 'Z') {
					capitalLetter = true;
				}
			}
			if (figure && capitalLetter & smallLetter) {
				return true;
			}
		}
		return false;
	}



}
