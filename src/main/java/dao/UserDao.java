package dao;

import model.member.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class UserDao extends BaseDao {
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        session.update(user.getTicket());
        transaction.commit();
        session.close();
    }
}
