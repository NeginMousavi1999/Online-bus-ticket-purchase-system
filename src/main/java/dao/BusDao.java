package dao;

import enumuration.BusType;
import model.Bus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class BusDao extends BaseDao {

    public void save(Bus bus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bus);
        transaction.commit();
        session.close();
    }

    public List<Bus> get(BusType busType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Bus b where b.type=:busType";
        Query<Bus> query = session.createQuery(hql, Bus.class);
        query.setParameter("busType", busType);
        List<Bus> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
