package com.jcg.examples.services;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.jcg.examples.models.Transfer;

import java.util.LinkedList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService{

	private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Override
	public List<Transfer> selectAll() {
		SessionFactory sf = null;
		Session session = null;
		List<Transfer> tr_list = new LinkedList<Transfer>();
		try {			
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Transfer");
			tr_list = q.list();
		} catch (RuntimeException e) {
			try {				
				session.getTransaction().rollback();
			} catch (RuntimeException rbe) {
                logger.error("Exception in rollback", rbe);
			}
		} finally {
			if(session != null) {				
				session.close();
			}
			if(sf != null) {
				sf.close();	       				
			}
		}
		return tr_list;
	}

	@Override
	public List<Transfer> selectAll(int id) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = null;
		List<Transfer> tr_list = new LinkedList<Transfer>();
		try {
			session = sf.openSession();
			Query q = session.createQuery("from Transfer where client_id = :id");
			q.setParameter("id", id);
			tr_list = q.list();
		} catch (RuntimeException e) {
            logger.error("Exception in selectAll",
                    new RuntimeException("Error while transaction performing"));
			throw new RuntimeException("Error while transaction performing");
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return tr_list;
	}

	@Override
	public void save(Transfer tr) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session sess = null;

		try {

			sess = sf.openSession();
			Transaction tx = null;

			try {
				tx = sess.beginTransaction();
				sess.save(tr);
				tx.commit();
			} catch(RuntimeException e2) {
				try {
					if(tx != null) tx.rollback();
				} catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
					throw new RuntimeException("Rollback error");
				}
                logger.error("Exception in deleteById",
                        new RuntimeException("Error while performing transaction"));
				throw new RuntimeException("Error while performing transaction");
			}

		} catch (RuntimeException e1) {
            logger.error("Exception in openSession", new RuntimeException(e1.getMessage()));
			throw new RuntimeException(e1.getMessage());
		} finally {
			if(sess != null) sess.close();
		}
	}

}
