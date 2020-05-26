package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
   @Autowired
   private IOrdersService iOrdersService;

   //查询全部订单未分页
//   @RequestMapping("/findAll.do")
//   public ModelAndView findAll(Orders orders) throws Exception {
//        ModelAndView mv =new ModelAndView();
//        List<Orders> ordersList = iOrdersService.findAll();
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//
//    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */

    @RequestMapping("/findAll.do")
//    @Secured("ROLE_ADMIN")
   public ModelAndView findAll(@RequestParam(name = "page",required =true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4" )Integer size) throws Exception {
        ModelAndView mv =new ModelAndView();
       List<Orders> ordersList = iOrdersService.findAll(page,size);
        PageInfo  pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders--list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId){
        ModelAndView mv = new ModelAndView();
         Orders orders=  iOrdersService.findById(ordersId);
         mv.addObject("orders",orders);
         mv.setViewName("orders-show");
         return mv;

    }
}
