package service;

import dao.ManagerDao;
import model.member.Manager;

/**
 * @author Negin Mousavi
 */
public class ManagerService {
    ManagerDao managerDao = new ManagerDao();

    public void init() {
        Manager manager = Manager.getInstance();
        manager.setUsername("admin");
        manager.setPassword("admin");
        manager.setNationalCode("0021899436");
        manager.setPhoneNumber("09381408292");
        managerDao.save(manager);
    }

    public Manager getManager() {
        return managerDao.get();
    }
}
