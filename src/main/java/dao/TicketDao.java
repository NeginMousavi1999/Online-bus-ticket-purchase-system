package dao;

import model.ticket.Ticket;
import model.ticket.TicketViewRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.Date;
import java.util.List;

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

    public List<Ticket> showTicketsByPaging(int start, int pageSize, TicketViewRequest request) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Ticket.class, "t");
        criteria.setFirstResult(start);
        criteria.setMaxResults(pageSize);

        SimpleExpression originCond = Restrictions.eq("t.origin", request.getOrigin());
        SimpleExpression destinationCond = Restrictions.eq("t.destination", request.getDestination());
        SimpleExpression dateCond;
        LogicalExpression and = Restrictions.and(originCond, destinationCond);
        if (request.getDate() != null) {
            dateCond = Restrictions.eq("t.departureDate", request.getDate());
            and = Restrictions.and(and, dateCond);
        }
        criteria.add(and);

        List<Ticket> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }

    public Ticket getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        transaction.commit();
        session.close();
        return ticket;
    }

    public Date getDateById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        transaction.commit();
        session.close();
        return ticket.getDepartureTime();
    }
}
