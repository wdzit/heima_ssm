package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll() throws Exception;

    void saveRole(Role role) throws Exception;

    List<Role> findByUserId(String userId) throws Exception;

    Role findById(String roleId) throws  Exception;

    void saveRoleAndPermission(String roleId, String[] permissionIds);
}
