package com.jcg.examples.services;

import com.jcg.examples.models.Card;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;

public class CardServiceImpl implements CardService{

    private static final Logger logger = Logger.getLogger(CardServiceImpl.class);

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

        if(cards == null) {
            logger.error("Exception in findByClientId", new RuntimeException("Card not found"));
            throw new RuntimeException("Card not found");
        }
        return cards;
    }

    @Override
    public List<Card> selectAll() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = null;
        List<Card> cards = new LinkedList<Card>();

        try {
            session = sf.openSession();
            Query q = session.createQuery("from Card");
            cards = q.list();
        } catch (RuntimeException e) {
            logger.error("Exception in selectAll",
                    new RuntimeException("Error while transaction performing"));
            throw new RuntimeException("Error while transaction performing");
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return cards;
    }

    @Override
    public void save(Card card) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                sess.save(card);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in save",
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
    public void update(Card card) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                String hql = "update Card set card_number=:card_number, " +
                        "account_id=:account_id, client_id=:client_id where id=:id";
                sess.createQuery(hql)
                        .setParameter("id", card.getId())
                        .setParameter("card_number", card.getCard_number())
                        .setParameter("account_id", card.getAccount_id())
                        .setParameter("client_id", card.getClient_id())
                        .executeUpdate();
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                logger.error("Exception in update",
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
                Card card = (Card) sess.get(Card.class, id);
                sess.delete(card);
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

    @Override
    public Card findById(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        Card card = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                Query q = sess.createQuery("from Card where id = :id");
                q.setParameter("id", id);
                card = (Card)q.list().get(0);
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

        if(card == null) {
            logger.error("Exception in findById", new RuntimeException("Card not found"));
            throw new RuntimeException("Card not found");
        }
        return card;
    }

    @Override
    public Card findByNumber(String card_number) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        Card card = null;

        try {

            sess = sf.openSession();
            Transaction tx = null;

            try {
                tx = sess.beginTransaction();
                Query q = sess.createQuery("from Card where card_number = :card_number");
                q.setParameter("card_number", card_number);
                card = (Card)q.list().get(0);
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

        if(card == null) {
            logger.error("Exception in findByNumber", new RuntimeException("Card not found"));
            throw new RuntimeException("Card not found");
        }
        return card;
    }
}
