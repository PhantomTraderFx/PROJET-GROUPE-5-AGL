package com.G5.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.G5.model.Commande;
import com.G5.model.Menu;
import com.G5.util.HibernateUtil;

public class MenuDao implements IMenuDao {

	@Override
	public void saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(menu);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

		
	}

	@Override
	public Menu getMenuById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAllMenus() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        List < Menu > menus = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            menus = session.createQuery("from Menu").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return menus;
	}

	@Override
	public void deleteMenu(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Menu recupererPlat(String jour) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        Menu menu = new Menu();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // Le scrypte de la requ�te fait en sorte d'etre en String en tenant compte des param�tres de la m�thode
    		String query = "SELECT * FROM `menu` WHERE ((`menu`.`JOUR` ='"+jour+"'))";

            //Ex�cution de la requ�te et la mise de son r�sultat dans la liste de commande
            menu = (Menu) session.createSQLQuery(query).addEntity(Menu.class).uniqueResult();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return menu;

	}

}
