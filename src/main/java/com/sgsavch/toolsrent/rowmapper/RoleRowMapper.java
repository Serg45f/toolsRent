package com.sgsavch.toolsrent.rowmapper;

import com.sgsavch.toolsrent.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Role.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("role_name"))
                .permission(resultSet.getString("permission"))
                .build();
    }
}
