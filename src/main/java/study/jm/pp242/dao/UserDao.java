package study.jm.pp242.dao;

import study.jm.pp242.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    User get(long id);

    void update(long id, User updatedUser);

    void delete(long id);

    List<User> listUsers();

    void cleanUsersTable();

}
