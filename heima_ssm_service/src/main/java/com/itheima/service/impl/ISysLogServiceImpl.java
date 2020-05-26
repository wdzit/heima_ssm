package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISysLogServiceImpl  implements ISysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void saveSysLog(SysLog sysLog) throws Exception {
        sysLogDao.saveSysLog(sysLog);

    }
}
