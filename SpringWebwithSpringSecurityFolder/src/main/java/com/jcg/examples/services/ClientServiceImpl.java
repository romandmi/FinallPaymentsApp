package com.jcg.examples.services;


import com.jcg.examples.models.Client;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;

public class ClientServiceImpl implements ClientService{

    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public Client findByUserId(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        Client client = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                //FIXME
                List<Client> allclient = sess.createQuery("from Client").list();
                for (Client c: allclient) {
                    if (c.getUser_id() == id){
                        client = c;
                    }
                }
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

        if(client == null) {
            logger.error("Exception in findByUserId", new RuntimeException("Client not found"));
            throw new RuntimeException("Client not found");
        }
        return client;
    }

    @Override
    public List<Client> selectAll() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = null;
        List<Client> c_list = new LinkedList<Client>();

        try {
            session = sf.openSession();
            Query q = session.createQuery("from Client");
            c_list = q.list();
        } catch (RuntimeException e) {
            logger.error("Exception in transaction",
                    new RuntimeException("Error while transaction performing"));
            throw new RuntimeException("Error while transaction performing");
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return c_list;
    }

    @Override
    public void save(Client client) {
        {

//        Client client = null;
//        //TODO
//        return client;

            SessionFactory sf = new Configuration().configure().buildSessionFactory();

            Session sess = null;

            try {

                sess = sf.openSession();
                Transaction tx = null;

                try {
                    tx = sess.beginTransaction();
                    sess.save(client);
                    tx.commit();
                } catch (RuntimeException e2) {
                    try {
                        if (tx != null) tx.rollback();
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
                if (sess != null) sess.close();
            }
        }
    }

    @Override
    public void update(Client client) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                String hql = "update Client set first_name =:first_name, last_name=:last_name, " +
                        "surname=:surname,user_id=:user_id where id=:id";
                sess.createQuery(hql)
                        .setParameter("id", client.getId()).setParameter("first_name", client.getFirst_name())
                        .setParameter("last_name", client.getLast_name())
                        .setParameter("surname", client.getSurname())
                        .setParameter("user_id", client.getUser_id())
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
                Client client = (Client) sess.get(Client.class, id);
                sess.delete(client);
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
    public Client findById(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        Client client = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                Query q = sess.createQuery("from Client where id = :id");
                q.setParameter("id", id);
                client = (Client)q.list().get(0);
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

        if(client == null) {
            logger.error("Exception in findById", new RuntimeException("Client not found"));
            throw new RuntimeException("Client not found");
        }
        return client;

    }
}
