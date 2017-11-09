package com.jcg.examples.services;

import com.jcg.examples.models.BankAccount;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;

public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger logger = Logger.getLogger(BankAccountServiceImpl.class);

    @Override
    public List<BankAccount> selectAll() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = null;
        List<BankAccount> tr_list = new LinkedList<BankAccount>();
        try {
            session = sf.openSession();
            Query q = session.createQuery("from BankAccount");
            tr_list = q.list();
        } catch (RuntimeException e) {
            logger.error("Exception in selectAll", new RuntimeException("Error while transaction performing"));
            throw new RuntimeException("Error while transaction performing");
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return tr_list;
    }

    @Override
    public void changeStatusById(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                BankAccount acc = (BankAccount) sess.get(BankAccount.class, id);
                if (acc.getIs_blocked() == true) {
                    acc.setIs_blocked(false);
                }
                else{
                    acc.setIs_blocked(true);
                }
                sess.save(acc);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in transaction",
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

    @Override
    public BankAccount getById(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        BankAccount bankAccount = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                Query q = sess.createQuery("from BankAccount where id = :id");
                q.setParameter("id", id);
                bankAccount = (BankAccount)q.list().get(0);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in transaction", new RuntimeException("Error while making query"));
                throw new RuntimeException("Error while making query");
            }

        } catch (RuntimeException e1) {
            logger.error("Exception in openSession", new RuntimeException(e1.getMessage()));
            throw new RuntimeException(e1.getMessage());
        } finally {
            if(sess != null) sess.close();
        }

        if(bankAccount == null) {
            logger.error("Exception in bankAccount", new RuntimeException("BankAccount  not found"));
            throw new RuntimeException("BankAccount  not found");
        }
        return bankAccount;

    }

    @Override
    public void save(BankAccount account) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                sess.save(account);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in transaction",
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

    @Override
    public void update(BankAccount account) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                String hql = "update BankAccount set balance =:balance, is_blocked=:is_blocked where id=:id";
                sess.createQuery(hql)
                        .setParameter("id", account.getId())
                        .setParameter("balance", account.getBalance())
                        .setParameter("is_blocked", account.getIs_blocked())
                        .executeUpdate();
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in transaction",
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

    @Override
    public void deleteById(Long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                BankAccount bankAccount = (BankAccount) sess.get(BankAccount.class, id);
                sess.delete(bankAccount);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in transaction",
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
