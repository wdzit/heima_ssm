package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    /**
     * 类型转化
     */
    private DateStringEditor dateStringEditor = new DateStringEditor();
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,dateStringEditor);

    }

    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product>  products= iProductService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product_list1");
        return mv;
    }
   @RequestMapping("/save.do")
    public String saveProduct(Product product){
        iProductService.save(product);
       return "redirect:/product/findAll.do";
    }






}
