package study.jm.pp242.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jm.pp242.dao.UserDao;
import study.jm.pp242.model.Role;
import study.jm.pp242.model.User;

import java.util.*;

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
        Set<Role> userRole = Collections.singleton(new Role("ROLE_USER"));
        Set<Role> userAndAdminRoles = new HashSet<>(Arrays.asList(new Role("ROLE_USER"), new Role ("ROLE_ADMIN")));

        userDao.cleanUsersTable();

        initUsersList.add(new User("Vladimir", "Putin", "putya@mainbunker.ru", "13-12-19xx", "USSR, Popamira 123"
                , "vvp", "$2y$12$./Fk/mWc49gs0xdJjazB4.DSmaI7K79/mVcXQcR3QPY2gQrzsiGNu", userAndAdminRoles));
        initUsersList.add(new User("Dmitriy", "Peskov", "pesok@kremlin.ru", "13-12-19xx", "USSR, Popamira 456"
                , "pesok", "$2y$12$./Fk/mWc49gs0xdJjazB4.DSmaI7K79/mVcXQcR3QPY2gQrzsiGNu", userRole));
        initUsersList.add(new User("Uasya", "Vatnikov", "vata@mail.ru", "13-12-19xx", "USSR, Popamira 789"
                , "vata", "$2y$12$./Fk/mWc49gs0xdJjazB4.DSmaI7K79/mVcXQcR3QPY2gQrzsiGNu", userRole));
        initUsersList.add(new User("Alexey", "Navalniy", "leha@better-future.ru", "13-12-19xx", "USSR, Moscow"
                , "leha", "$2y$12$./Fk/mWc49gs0xdJjazB4.DSmaI7K79/mVcXQcR3QPY2gQrzsiGNu", userAndAdminRoles));
        initUsersList.add(new User("Vladimir", "Solovyov", "pomet@trash.ru", "13-12-19xx", "USSR, Popamira 666"
                , "pomet", "$2y$12$./Fk/mWc49gs0xdJjazB4.DSmaI7K79/mVcXQcR3QPY2gQrzsiGNu", userRole));
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
    public User findUserByUsername(String login) {
        return userDao.findUserByLogin(login);
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

}
