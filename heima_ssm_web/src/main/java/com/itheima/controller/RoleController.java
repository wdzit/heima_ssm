package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IPermissionService;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IRoleService iRoleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
      List<Role> roleList =   iRoleService.findAll();
      ModelAndView mv = new ModelAndView();
      mv.addObject("roleList",roleList);
      mv.setViewName("role-list");
      return mv;

    }
    @RequestMapping("/save.do")
    public String Save(Role role) throws Exception {

        iRoleService.saveRole(role);

        return "redirect:findAll.do";


    }
@RequestMapping("/findRoleByIdAndAllPermission.do")
   public ModelAndView  findRoleByIdAndAllPermission(@RequestParam(name="id") String roleId) throws Exception{
        ModelAndView mv =new ModelAndView();
        Role role = iRoleService.findById(roleId);
        List<Permission> permissions = iPermissionService.findByRoleId(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");

        return mv;
   }
   @RequestMapping("/addRoleToUser.do")
   public String saveRoleToUser(@RequestParam(name="roleId") String roleId,@RequestParam(name="ids") String[] permissionIds){
        iRoleService.saveRoleAndPermission(roleId,permissionIds);


        return "redirect:findAll.do";
   }
}
