package com.nixsolutions.hibernate.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			
			transaction = session.beginTransaction();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
	}

}
