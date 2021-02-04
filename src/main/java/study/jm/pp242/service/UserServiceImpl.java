package study.jm.pp242.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jm.pp242.dao.UserDao;
import study.jm.pp242.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void addInitUsersToDB() {
        List<User> initUsersList = new ArrayList<>();

        userDao.cleanUsersTable();
        initUsersList.add(new User("Vladimir", "Putin", "putya@mainbunker.ru", "13-12-19xx", "USSR, Popamira 123"));
        initUsersList.add(new User("Dmitriy", "Peskov", "pesok@kremlin.ru", "13-12-19xx", "USSR, Popamira 456"));
        initUsersList.add(new User("Uasya", "Vatnikov", "vata@mail.ru", "13-12-19xx", "USSR, Popamira 789"));
        initUsersList.add(new User("Alexey", "Navalniy", "leha@better-future.ru", "13-12-19xx", "USSR, Moscow"));
        initUsersList.add(new User("Vladimir", "Solovyov", "pomet@trash.ru", "13-12-19xx", "USSR, Popamira 666"));
        for (User user : initUsersList) {
            userDao.add(user);
        }
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(long id) {
        return userDao.get(id);
    }

    @Transactional
    @Override
    public void update(long id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

}
