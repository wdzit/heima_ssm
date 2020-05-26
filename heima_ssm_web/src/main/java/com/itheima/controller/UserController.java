package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IRoleService;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iuserService;
    @Autowired
    private IRoleService iRoleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page" , required = true,defaultValue = "1")Integer page,
                                @RequestParam(name="size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList= iuserService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(userInfoList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;

    }
    @RequestMapping("/save.do")
    public ModelAndView save(UserInfo userInfo){
        ModelAndView mv =new ModelAndView();
        iuserService.save(userInfo);
        mv.addObject("userList",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findByUsersId(@RequestParam(name="id",required = true) String userid) throws Exception{
        ModelAndView  mv = new ModelAndView();
       UserInfo user=   iuserService.findById(userid);
       mv.addObject("user",user);
       mv.setViewName("user-show1");
       return mv;

    }

    /**
     * 查询用户以及用户可以添加的角色
     * @param userId
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true) String userId) throws Exception {
        UserInfo user = iuserService.findById(userId);
        List<Role> roles =iRoleService.findByUserId(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;


    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name="userId",required = true) String userId,@RequestParam(name="ids",required = true) String[] roleIds) throws Exception {
           iuserService.saveUserAndRole(userId,roleIds);
           return "redirect:findAll.do";
    }
}
