package com.itheima.dao;

import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results(
            {
                    @Result(id=true,property = "id",column = "id"),
                    @Result(property = "email" ,column = "email"),
                    @Result(property = "username" ,column = "username"),
                    @Result(property = "password" ,column = "password"),
                    @Result(property = "phoneNum" ,column = "phoneNum"),
                    @Result(property = "status" ,column = "status"),
                    @Result(property = "roles" ,column = "id" ,javaType = java.util.List.class,
                            many = @Many(select =("com.itheima.dao.IRolesDao.findByUserInfoId") )),
            }

    )
    UserInfo findByUserName(String username) throws Exception;


    @Select("select * from users")
    public  List<UserInfo> findAll();
  @Insert("insert into users(id,email,username,password,phoneNum,status) values (#{id},#{email},#{username},#{password},#{phoneNum},#{status} )")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results(
            {
                    @Result(id=true,property = "id",column = "id"),
                    @Result(property = "email" ,column = "email"),
                    @Result(property = "username" ,column = "username"),
                    @Result(property = "password" ,column = "password"),
                    @Result(property = "phoneNum" ,column = "phoneNum"),
                    @Result(property = "status" ,column = "status"),
                    @Result(property = "roles" ,column = "id" ,javaType = java.util.List.class,
                            many = @Many(select =("com.itheima.dao.IRolesDao.findByUserId") )),
            }

    )
    UserInfo findById(String userid) throws Exception;

    /**
     * 使用@param 注解是为了区分两个String类型的数据
     * @param userId
     * @param roleId
     * @throws Exception
     */
   @Insert("insert into users_role(userId,roleId) values (#{userId},#{roleId})")
   void saveUserAndRole(@Param("userId") String userId,@Param("roleId") String roleId) throws Exception;
}
