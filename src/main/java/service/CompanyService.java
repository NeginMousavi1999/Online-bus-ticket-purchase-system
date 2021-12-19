package service;

import dao.CompanyDao;
import model.Company;

/**
 * @author Negin Mousavi
 */
public class CompanyService {
    CompanyDao companyDao = new CompanyDao();

    public void save(Company company) {
        companyDao.save(company);
    }
}
