package study.jm.pp242.service;

import study.jm.pp242.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    User get(long id);

    void update(long id, User updatedUser);

    void delete(long id);

    List<User> listUsers();

    void cleanUsersTable();

    void addInitUsersToDB();
}

