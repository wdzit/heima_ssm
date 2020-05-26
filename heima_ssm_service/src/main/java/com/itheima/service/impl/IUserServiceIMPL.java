package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class IUserServiceIMPL implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo name = null;
        try {
            name = iUserDao.findByUserName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将从数据库中查出的数据封装到实体类中，
//        将实体类转换为UserDetails实现类User对象
//        getAuthority()该方法返回的是角色信息的集合
//        "{noop}"+name.getPassword()

    //    User user =new User(name.getUsername(),"{noop}"+name.getPassword(),getAuthority(name.getRoles()));
        /**
         * 根据用户当时的状态码屏蔽用户的权限
         *
         * name.getStatus()==0?false:true
         */

        User user =new User(name.getUsername(),name.getPassword(),name.getStatus()==0?false:true,true,true,true,getAuthority(name.getRoles()));

        return user;
    }

    /**
     * 因为在springSecurity.xml文件中声明access="ROLE_USER" 必须有ROLE_USER的角色
     * 该方法就是获得角色信息
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
            List<SimpleGrantedAuthority> list =new ArrayList<>();
            for (Role role:roles
            ) {
                list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }

            return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return iUserDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        /**
         * 给用户密码加密
         */
        String encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);

        iUserDao.save(userInfo);

    }

    @Override
    public UserInfo findById(String userid) throws Exception {
        return iUserDao.findById(userid);
    }

    @Override
    public void saveUserAndRole(String userId, String[] roleIds) throws Exception {
        for (String roleId:roleIds
             ) {
            iUserDao.saveUserAndRole(userId,roleId);
        }
    }
}
