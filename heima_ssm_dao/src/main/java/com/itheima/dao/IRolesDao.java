package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRolesDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    List<Role> findByUserInfoId(String userInfoId);



    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    @Results(
            {
                                     @Result(id=true,property = "id",column = "id"),
                    @Result(property ="roleName",column = "roleName"),
                    @Result(property ="roleDesc",column = "roleDesc"),
                    @Result(property ="permissions",column = "id",javaType = java.util.List.class,
                            many = @Many(select = ("com.itheima.dao.IPermissionDao.findByRoleId"))
                    ),

            }
    )
    List<Role> findByUserId(String userId);
    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(id,roleName,roleDesc) values (#{id},#{roleName},#{roleDesc})")
    void saveRole(Role role) throws Exception;

    @Select("select * from role where id  not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherByUserId(String userId) throws Exception;

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;

    @Insert("insert into role_permission (permissionId,roleId) values(#{permission},#{roleId})")
    void saveRoleAndPermission(@Param("roleId") String roleId, @Param("permission")String permission);
}
