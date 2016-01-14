package com.nixsolutions;

import com.nixsolutions.util.HibernateUtil;

public class HibernateFirstTaskMain {

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.closeSessionFactory();
	}
}
