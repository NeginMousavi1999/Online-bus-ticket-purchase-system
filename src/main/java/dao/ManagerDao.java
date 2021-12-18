package dao;

import model.member.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class ManagerDao extends BaseDao {
    public void save(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(manager);
        transaction.commit();
        session.close();
    }

    public Manager get() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Manager manager = session.get(Manager.class, 1);
        transaction.commit();
        session.close();
        return manager;
    }
}
