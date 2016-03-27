package com.finance.tracker.model.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.*;

import com.finance.tracker.exception.FinanceTrackerException;
import com.finance.tracker.model.Account;
import com.finance.tracker.model.IAccount;
import com.finance.tracker.model.IBudget;
import com.finance.tracker.model.IUser;
import com.finance.tracker.model.User;

public class UserDAO implements IUserDAO {
	@PersistenceContext
	private EntityManager manager = DaoUtils.getEmfactory().createEntityManager();
	
	@Override
	public int createUser(IUser user) {
		if (user != null) {
			try {
				manager.getTransaction().begin();
				manager.persist(user);
				manager.getTransaction().commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
					throw e;
				}
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
<<<<<<< HEAD
		}
		return user.getUserId();
=======
		} 
>>>>>>> 9e3353e279baef657999ff56707dc364a6c6f350
	}

	@Override
	public void deleteUser(int index) {
		if (index > 0) {
			try {
				manager.getTransaction().begin();
				IUser user = manager.find(IUser.class, index);
				manager.remove(user);
				manager.getTransaction().commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
					throw e;
				}
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public User getUser(int id) {
		if (id > 0) {
			try {
				manager.getTransaction().begin();
				User user = manager.find(User.class, id);
				manager.getTransaction().commit();
				return user;
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
					throw e;
				}
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public Collection<IUser> getAllUsers() {
		List<IUser> listOfAllFinanceOperations = manager.createQuery("SELECT u FROM user u").getResultList();
		return listOfAllFinanceOperations;
	}

	@Override
	public void updateUser(IUser user) {
		if (user != null) {
			try {
				manager.getTransaction().begin();
				manager.merge(user);
				manager.getTransaction().commit();
			} catch (RuntimeException e) {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
					throw e;
				}
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}

	}

	public IUser getUserByMail(String email) {
		if (email != null && email.length() > 0) {
			String txtQuery = "SELECT u FROM user u WHERE u.email = '" + email + "'";
			TypedQuery<IUser> query = manager.createQuery(txtQuery, IUser.class);
			try {
				return query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		} else {
			try {
				throw new FinanceTrackerException();
			} catch (FinanceTrackerException e) {
				e.getMessage();
			}
		}
		return null;
	}

	public List<IAccount> getAllAccounts(int id) {
		if (id > 0) {
			Query query = manager.createQuery("Select a " + "from Account a " + "where a.user_id:=id", IAccount.class);
			try {
				return (List<IAccount>) query.getResultList();
			} catch (NoResultException e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
