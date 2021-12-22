package service;

import dao.UserDao;
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
