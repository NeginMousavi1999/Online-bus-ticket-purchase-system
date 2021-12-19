package dao;

import enumuration.BusType;
import model.Bus;
import model.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CompanyDao extends BaseDao {
    public void save(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
    }

    public List<Company> getByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Company c where c.name=:name";
        Query<Company> query = session.createQuery(hql, Company.class);
        query.setParameter("name", name);
        List<Company> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
