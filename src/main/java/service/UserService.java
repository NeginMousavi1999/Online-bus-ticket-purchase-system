package service;

import dao.ManagerDao;
import dao.UserDao;
import model.member.Manager;
import model.member.User;

/**
 * @author Negin Mousavi
 */
public class UserService {
    UserDao userDao = new UserDao();

    public void save(User user) {
        userDao.save(user);
    }
}
