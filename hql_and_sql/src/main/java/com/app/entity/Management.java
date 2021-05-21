package com.app.entity;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Management {

	Scanner sc=new Scanner(System.in);
	private static SessionFactory sf;
	static {
		Configuration cfg=new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder();
		builder.applySettings(cfg.getProperties());
		ServiceRegistry sr=builder.build();
		sf=cfg.buildSessionFactory(sr);
	}
	
	@SuppressWarnings("unchecked")
	public void hql() {
		Session session=sf.openSession();
		Query query=session.createQuery("from Student");
		//		Query query=session.createQuery("from student");map pojo not table name
		query.list().forEach(System.out::println);
	}
	@SuppressWarnings("unchecked")
	public void HQLPagination() {
		Session session=sf.openSession();
		Query query=session.createQuery("from Student");
		//		Query query=session.createQuery("from student");map pojo not table name
		query.setFirstResult(0);
		query.setMaxResults(4);
		query.list().forEach(System.out::println);
	}
	@SuppressWarnings("unchecked")
	public void sql() {
		Session session=sf.openSession();
		SQLQuery sq=session.createSQLQuery("select * from student").addEntity(Student.class);
		sq.list().forEach(System.out::println);
	
	}
	@SuppressWarnings("unchecked")
	public void SQLPagination() {
		Session session=sf.openSession();
		SQLQuery sq=session.createSQLQuery("select * from student").addEntity(Student.class);
		sq.setFirstResult(4);
		sq.setMaxResults(6);
		sq.list().forEach(System.out::println);

	}
	
	public void HQLUpdate() {
		Session session=sf.openSession();
		Query query=session.createQuery("select name from Student");
		query.list().forEach(System.out::println);
	}
	public static void main(String[] args) {
		Management m=new Management();
		//m.hql();
		//m.sql();
		m.HQLPagination();
		m.SQLPagination();
		m.HQLUpdate();
	}

}
