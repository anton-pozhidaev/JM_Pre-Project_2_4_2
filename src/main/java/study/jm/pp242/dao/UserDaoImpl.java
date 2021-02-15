package study.jm.pp242.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.jm.pp242.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public User get(long id) {
        User usr = em.find(User.class, id);
        return usr;
    }

    @Override
    public User findUserByLogin(String login) {
        TypedQuery<User> query = em.createQuery("SELECT user FROM User user WHERE user.login=:l", User.class);
        query.setParameter("l", login);
        User usr = query.getSingleResult();
        return usr;
    }

    @Override
    public void update(long id, User updatedUser) {
        User userToBeUpdated = em.find(User.class, id);
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setBirthday(updatedUser.getBirthday());
        userToBeUpdated.setAddress(updatedUser.getAddress());
    }

    @Override
    public void delete(long id) {
        User usr = em.find(User.class, id);
        em.remove(usr);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = em.createQuery("SELECT user from User user", User.class);
        return query.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        em.createQuery("DELETE FROM User").executeUpdate();
        em.createQuery("DELETE FROM Role").executeUpdate();
    }
}
