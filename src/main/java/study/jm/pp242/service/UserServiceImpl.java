package study.jm.pp242.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jm.pp242.dao.RoleDao;
import study.jm.pp242.dao.UserDao;
import study.jm.pp242.model.Role;
import study.jm.pp242.model.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void addInitUsersToDB() {

        List<User> initUsersList = new ArrayList<>();
        Set<Role> userRole = Collections.singleton(new Role("ROLE_USER"));
        Set<Role> adminRole = Collections.singleton(new Role("ROLE_ADMIN"));
        Set<Role> viewerRole = Collections.singleton(new Role("ROLE_VIEWER"));
        Set<Role> userAndAdminRoles = new HashSet<>();
        userAndAdminRoles.addAll(userRole);
        userAndAdminRoles.addAll(adminRole);

        initUsersList.add(new User("Vladimir", "Putin", "putya@mainbunker.ru"
                , "13-12-19xx", "USSR, Popamira 123"
                , "vvp", "1234", adminRole));

        initUsersList.add(new User("Dmitriy", "Peskov", "pesok@kremlin.ru",
                "13-12-19xx", "USSR, Popamira 456"
                , "pesok", "1234", userRole));

        initUsersList.add(new User("Uasya", "Vatnikov", "vata@mail.ru",
                "13-12-19xx", "USSR, Popamira 789"
                , "vata", "1234", viewerRole));

        initUsersList.add(new User("Alexey", "Navalniy", "leha@better-future.ru",
                "13-12-19xx", "USSR, Moscow"
                , "leha", "1234", userAndAdminRoles));

        initUsersList.add(new User("Vladimir", "Solovyov", "pomet@trash.ru",
                "13-12-19xx", "USSR, Popamira 666"
                , "pomet", "1234", userRole));
        for (User user : initUsersList) {
            this.add(user);
        }
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(long id) {
        return userDao.get(id);
    }

    @Transactional
    @Override
    public void update(User updatedUser) {
        String newPassword = updatedUser.getPassword();
        String oldPassword = userDao.get(updatedUser.getId()).getPassword();

        if (newPassword.equals("")) {
            updatedUser.setPassword(oldPassword);
        } else {
            updatedUser.setPassword(passwordEncoder.encode(newPassword));
        }
        userDao.update(updatedUser);
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
}
