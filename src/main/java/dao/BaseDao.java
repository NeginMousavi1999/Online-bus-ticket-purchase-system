package dao;

import org.hibernate.SessionFactory;
import util.CreateSessionFactory;

/**
 * @author Negin Mousavi
 */
public class BaseDao {
    SessionFactory sessionFactory = CreateSessionFactory.getInstance();
}
