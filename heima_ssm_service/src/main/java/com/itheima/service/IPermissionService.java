package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll() throws Exception;

    List<Permission> findByRoleId(String roleId) throws  Exception;
}
