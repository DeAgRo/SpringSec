package web.dao;

import web.model.Role;

import java.util.List;

/**
 * @author Ali Veliev 09.11.2021
 */

public interface RoleDao {
    List<Role> allRoles();
    Role getRole(Long id);
}
