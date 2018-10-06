package ir.university.hiber.main;

import java.util.Date;

import org.hibernate.Session;

import ir.university.hiber.core.HibernateUtil;

public class TestAndDeleteAfterThat {
	
	public static void main(String[] args) {
		try {
			HibernateUtil.setUp();
			Session session = HibernateUtil.getCurrentSession();
			session.beginTransaction();
			session.getTransaction().commit();
			session.close();
			HibernateUtil.tearDown();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
