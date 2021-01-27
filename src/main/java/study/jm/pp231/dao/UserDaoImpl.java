package study.jm.pp231.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import study.jm.pp231.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDaoImpl(@Qualifier("getEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public User get(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User usr = em.find(User.class, id);

        em.getTransaction().commit();
        return usr;
    }

    @Override
    public void update(long id, User updatedUser) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User userToBeUpdated = em.find(User.class, id);
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setBirthday(updatedUser.getBirthday());
        userToBeUpdated.setAddress(updatedUser.getAddress());

        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User usr = em.find(User.class, id);
        em.remove(usr);

        em.getTransaction().commit();
    }

    @Override
    public List<User> listUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<User> query = em.createQuery("SELECT user from User user", User.class);

        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM User").executeUpdate();

        em.getTransaction().commit();
    }
}
