package study.jm.pp242.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.jm.pp242.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public Role findByRoleName(String roleName) {
        TypedQuery<Role> query = em.createQuery("SELECT role FROM Role role WHERE role.role=:r", Role.class);
        query.setParameter("r", roleName);
        Role role = query.getSingleResult();
        return role;
    }

    @Transactional
    @Override
    public Collection<Role> getAllRoles() {
        TypedQuery<Role> query = em.createQuery("SELECT role FROM Role role", Role.class);
        return query.getResultList();
    }
}
