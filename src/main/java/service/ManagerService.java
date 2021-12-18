package service;

import dao.ManagerDao;
import model.Manager;

/**
 * @author Negin Mousavi
 */
public class ManagerService {
    ManagerDao managerDao = new ManagerDao();

    public void save(Manager manager) {
        managerDao.save(manager);
    }
}
