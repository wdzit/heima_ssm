package com.itheima.service.impl;

import com.itheima.dao.IRolesDao;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRolesDao iRolesDao;
    @Override
    public List<Role> findAll() throws Exception {
        return iRolesDao.findAll();

    }

    @Override
    public void saveRole(Role role) throws Exception {
        iRolesDao.saveRole(role);
    }

    @Override
    public List<Role> findByUserId(String userId) throws Exception {
        return iRolesDao.findOtherByUserId(userId);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRolesDao.findById(roleId);
    }

    @Override
    public void saveRoleAndPermission(String roleId, String[] permissionIds) {
        for (String permission: permissionIds
             ) {
            iRolesDao.saveRoleAndPermission(roleId,permission);
        }
    }
}
