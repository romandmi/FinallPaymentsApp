package com.jcg.examples.services;

import com.jcg.examples.models.Client;
import com.jcg.examples.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;

public class ClientServiceImpl implements ClientService{
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
                    throw new RuntimeException("Rollback error");
                }
                throw new RuntimeException("Error while making query");
            }

        } catch (RuntimeException e1) {
            throw new RuntimeException(e1.getMessage());
        } finally {
            if(sess != null) sess.close();
        }

        if(client == null) throw new RuntimeException("Client not found");
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
            throw new RuntimeException("Error while transaction performing");
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return c_list;
    }
}
