package study.jm.pp242.dao;

import study.jm.pp242.model.Role;

import java.util.Collection;
import java.util.Set;

public interface RoleDao {

    Role findByRoleName(String role);

    Collection<Role> getAllRoles();
}
