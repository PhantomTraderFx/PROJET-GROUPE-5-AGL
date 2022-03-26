package com.G5.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.G5.model.Alerte;
import com.G5.util.HibernateUtil;

public class AlerteDao implements IAlerteDao {

	@Override
	public void saveAlerte(Alerte alerte) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(alerte);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
	}

	@Override
	public void updateAlerte(Alerte alerte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Alerte getAlerteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alerte> getAllAlertes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAlerte(int id) {
		// TODO Auto-generated method stub
		
	}

}
