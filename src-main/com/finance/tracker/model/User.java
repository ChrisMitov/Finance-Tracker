package com.finance.tracker.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.exception.PasswordException;

@Entity
@Table(name = "user")
public class User implements IUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@JoinTable(name = "currency", joinColumns = @JoinColumn(name = "name"))
	@Enumerated(EnumType.STRING)
	@Column(name = "currency_name")
	private Currency currency;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_day")
	private Date jointedDate;
	@Column(name = "is_admin")
	private boolean isAdmin;
	
	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String password, Currency currency, boolean isAdmin,
			Date date) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setCurrency(currency);
		this.setStartDate(date);
		this.setIsAdmin(isAdmin);
	}

	public User(int id, String firstName, String lastName, String email, String password, Currency currency,
			boolean isAdmin, Date date) {
		this(firstName, lastName, email, password, currency, isAdmin, date);
		this.setUserId(id);
	}

	public void setIsAdmin(boolean is) {
		this.isAdmin = is;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	@Override
	public int getUserId() {
		return id;
	}

	@Override
	public void setUserId(int userId) {
		if (userId > 0) {
			this.id = userId;
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
	}

	@Override
	public void convertCurrency(Currency newCurrency) {
		this.currency = newCurrency;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		if (firstName != null && firstName.length() > 0) {
			this.firstName = firstName;
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		if (lastName != null && lastName.length() > 0) {
			this.lastName = lastName;
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		if (email != null && email.length() > 0) {
			this.email = email;
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
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
				throw new PasswordException("Password must contain a small letter, a capital letter and a figure!");
			} catch (PasswordException e) {
				e.getMessage();
			}
		}
	}

	@Enumerated(EnumType.STRING)
	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Enumerated(EnumType.STRING)
	@Override
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public Date getJointedDate() {
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

	@Override
	public void setStartDate(Date date) {
		if (date != null) {
			this.jointedDate = date;
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}

	}

}
