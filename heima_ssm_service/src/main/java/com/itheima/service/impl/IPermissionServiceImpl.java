package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return iPermissionDao.findAll();
    }

    @Override
    public List<Permission> findByRoleId(String roleId) throws Exception {
        return iPermissionDao.findOtherByRoleId(roleId);
    }
}
