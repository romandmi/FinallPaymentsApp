package com.jcg.examples.services;

import com.jcg.examples.models.Card;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;

public class CardServiceImpl implements CardService{

    @Override
    public List<Card> findByClientId(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        List<Card> cards = new LinkedList<>();

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                //FIXME
                List<Card> allcards = sess.createQuery("from Card").list();
                for (Card c: allcards) {
                    if (c.getClient_id() == id){
                        cards.add(c);
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

        if(cards == null) throw new RuntimeException("Card not found");
        return cards;
    }
}
