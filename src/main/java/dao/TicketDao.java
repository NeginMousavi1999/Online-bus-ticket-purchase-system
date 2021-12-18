package dao;

import model.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class TicketDao extends BaseDao {
    public void save(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }
}
