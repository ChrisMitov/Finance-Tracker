package com.finance.tracker.model.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoUtils {
	private static final String FINANCE_TRACKER_PERSISTENT_UNIT = "Finance-Tracker";
	private static final EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(FINANCE_TRACKER_PERSISTENT_UNIT);

	public static EntityManagerFactory getEmfactory() {
		return emfactory;
	}
	
}
