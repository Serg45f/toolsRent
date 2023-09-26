package com.sgsavch.toolsrent.repository.implementation;

import com.sgsavch.toolsrent.domain.Role;
import com.sgsavch.toolsrent.exception.ApiException;
import com.sgsavch.toolsrent.repository.RoleRepository;
import com.sgsavch.toolsrent.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import static java.util.Map.of;

import static com.sgsavch.toolsrent.enumeration.RoleType.ROLE_USER;
import static com.sgsavch.toolsrent.query.RoleQuery.*;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role {} to user id: {}", roleName, userId);
//        try {
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, of("roleName", roleName), new RoleRowMapper());
            log.info("Role: {}, to permissions: {}", role.getId(), role.getPermission());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY, of("userId", userId, "roleId", requireNonNull(role).getId()));
//        } catch (EmptyResultDataAccessException exception) {
//            throw new ApiException("No role found by name: " + ROLE_USER.name());
//
//        } catch (Exception e) {
//            throw new ApiException("An addRoleToUser error occurred.");
//        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }
}
