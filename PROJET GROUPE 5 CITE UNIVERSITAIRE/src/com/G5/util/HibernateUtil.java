package com.G5.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.G5.model.Alerte;
import com.G5.model.Commande;
import com.G5.model.Etudiant;
import com.G5.model.Menu;
import com.G5.model.Permission;

public class HibernateUtil {
	 private static SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                // Hibernate settings equivalent to hibernate.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/bd_cite2?useSSL=false");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	               // settings.put(Environment.HBM2DDL_AUTO, "create-drop");

	                configuration.setProperties(settings);

	                configuration.addAnnotatedClass(Etudiant.class);
	                configuration.addAnnotatedClass(Commande.class);
	                configuration.addAnnotatedClass(Permission.class);
	                configuration.addAnnotatedClass(Alerte.class);
	                configuration.addAnnotatedClass(Menu.class);

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
}
}
