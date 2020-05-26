package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {

    //查询所有产品信息
    @Select("select *from product")
    public List<Product> findAll() throws Exception;

    //保存用户信息
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus}) ")
    void save(Product product);

    //根据id查询产品的操作
    @Select("select * from product where id=#{productId}")
    Product findById(String id) throws Exception;
}
