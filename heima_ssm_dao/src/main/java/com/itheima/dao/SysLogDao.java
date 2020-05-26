package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SysLogDao {
     @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(# {visitTime},#{username},#{ip},#{url},#{executionTime},#{method}) ")
    public void saveSysLog(SysLog sysLog) throws Exception ;

}
