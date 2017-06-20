package com.waviz.example.dao;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	
	public int setValues(String name, String mobile, String mail,
			String pass, String add,String hobbies, String gender ){
		
		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setEmail(mail);
		user.setPassword(pass);
		user.setAddress(add);
		user.setHobbies(hobbies);
		user.setGender(gender);
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		factory.close();
		
		return 1;
	}
	
	public int getValues(String email, String pass){
		
		User user = new User();
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		
		user = session.get(User.class, email);
		try{
	
			System.out.println(user.getEmail()+""+user.getAddress()+""+user.getName());
		     
		if(user.getPassword().equals(pass)){
			return 1;
		}
		
		}
		finally{
		session.getTransaction().commit();
		session.close();
		factory.close();
		
		}
		return 0;
	}

}
