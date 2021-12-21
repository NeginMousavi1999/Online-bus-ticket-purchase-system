package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Negin Mousavi
 */
public class CreateSessionFactory {

    private static SessionFactory sessionFactory;

    public synchronized static SessionFactory getInstance() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    private CreateSessionFactory() {
    }
}
