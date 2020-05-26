package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IOrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IOrdersServiceImpl implements IOrdersService  {
    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public Orders findById(String ordersId) {
        return iOrdersDao.findById(ordersId);
    }
    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        /**
         * pageNum 表示分页查询的开始页码
         * pageSize代表每页记录条数
         */
        PageHelper.startPage(page, size);
        return iOrdersDao.findAll();

    }
}
