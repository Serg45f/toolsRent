package com.sgsavch.toolsrent.query;

public interface RoleQuery {
    String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO UserRoles (user_id, role_id) VALUES (:userId, :roleId)";
    String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM Roles WHERE role_name = :roleName";
}
