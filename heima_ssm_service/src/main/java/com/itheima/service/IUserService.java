package com.itheima.service;

import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    public List<UserInfo> findAll(int page,int size) throws Exception;

    void save(UserInfo userInfo);

    UserInfo findById(String userid) throws Exception;

    void saveUserAndRole(String userId, String[] roleIds) throws Exception;
}
