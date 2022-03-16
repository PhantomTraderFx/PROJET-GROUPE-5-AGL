package com.G5.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.G5.model.Etudiant;
import com.G5.model.Menu;
import com.G5.util.HibernateUtil;

public class EtudiantDao implements IEtudiantDao {

	@Override
	public void saveEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(etudiant);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		
	}

	@Override
	public void updateEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(etudiant);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		
	}

	@Override
	public Etudiant getEtudiantById(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Etudiant student = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    student= session.byId(Etudiant.class).getReference(id);
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

	@Override
	public List<Etudiant> getAllEtudiants() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Etudiant > students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            students = session.createQuery("from Etudiant").list();
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
	public void deleteEtudiant(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Etudiant Etudiant = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            Etudiant = session.get(Etudiant.class, id);
            // get student object
            session.delete(Etudiant);
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		
	}

	@Override
	public Etudiant getEtudiant(String nom, String prenom) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Etudiant etudiant = new Etudiant();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // Le scrypte de la requête fait en sorte d'etre en String en tenant compte des paramètres de la méthode
    		String query = "SELECT * FROM `etudiant` WHERE ((`etudiant`.`NOM` ='"+nom+"') AND (`etudiant`.`PRENOMS`='"+prenom+"'))";

            //Exécution de la requête et la mise de son résultat dans la liste de commande
            etudiant = (Etudiant) session.createSQLQuery(query).addEntity(Etudiant.class).uniqueResult();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return etudiant;
	}

	

}
