package com.G5.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.G5.model.Permission;
import com.G5.util.HibernateUtil;

public class PermissionDao implements IPermissionDao {

	@Override
	public void savePermission(Permission permission) {
		// TODO Auto-generated method stub
		 Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start the transaction
	            transaction = session.beginTransaction();

	            // save student object
	            session.save(permission);

	            // commit the transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	        }
		
	}

	@Override
	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(permission);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

		
	}

	@Override
	public Permission getPermissionById(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Permission student = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    student= session.byId(Permission.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getAllPermission() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Permission > students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            students = session.createQuery("from Permission").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return students;

	}

	@Override
	public void deletePermission(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Permission student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            student = session.get(Permission.class, id);
            // get student object
            session.delete(student);
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getAllPer() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Permission > listCommande = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // Le scrypte de la requête fait en sorte d'etre en String en tenant compte des paramètres de la méthode
    		String query = "SELECT * FROM `permission` WHERE `permission`.`HEURE_ARRIVE` IS NULL";

            //Exécution de la requête et la mise de son résultat dans la liste de commande
            listCommande = session.createSQLQuery(query).addEntity(Permission.class).list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return listCommande;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getAllHistoPer() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Permission > listCommande = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // Le scrypte de la requête fait en sorte d'etre en String en tenant compte des paramètres de la méthode
    		String query = "SELECT * FROM `permission` WHERE `permission`.`HEURE_ARRIVE` IS NOT NULL";

            //Exécution de la requête et la mise de son résultat dans la liste de commande
            listCommande = session.createSQLQuery(query).addEntity(Permission.class).list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return listCommande;
	}

	
	
}
