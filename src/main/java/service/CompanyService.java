package service;

import dao.CompanyDao;
import model.Company;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CompanyService {
    CompanyDao companyDao = new CompanyDao();

    public void save(Company company) {
        companyDao.save(company);
    }

    public Company getByName(String companyName) {
        List<Company> companies = companyDao.getByName(companyName);
        if (companies.size() == 0)
            throw new RuntimeException("*** did you entered wrong number? because we have n't company by this name ***");
        return companies.get(0);
    }
}
