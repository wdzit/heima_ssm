package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import net.sf.jsqlparser.statement.select.Join;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.security.Security;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;   //定义开始时间
    private Class clazz;      //访问的类
    private Method method;    //访问的方法
    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest servletRequest;  //在web.xml注册RequestContextListener  请求作用域对象
    /**
     * 前置通知：主要获取开始时间，执行的类是哪一个，执行的方法是哪一个
     *
     * @param joinPoint
     */
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();  //当前时间就是开始访问的时间

        clazz = joinPoint.getClass();//具体要访问的类

        String methodName = joinPoint.getSignature().getName();//获取访问方法的名称
        Object[] args = joinPoint.getArgs();//获取访问方法的参数

        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法

        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();

            }
            method = clazz.getMethod(methodName, classArgs);//获取有参数方法

        }


    }

    /**
     * 后置通知
     *
     * @param joinPoint
     */
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();    //获取访问时间


        //获取URL
        String url = "";


        if (clazz != null && method != null && clazz != LogAop.class) {
            //获取类上的@RequestMapping("/role")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();

                //获取方法上的  @RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }

            }
        }

        //获取访问的ip
        String ip = servletRequest.getRemoteAddr();

        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext(); //从上下文中获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();


        /**
         * 将日志相关的信息封装到SysLog对象
         */
        SysLog sysLog =new SysLog();

        sysLog.setIp(ip);
        sysLog.setExecutionTime(time);
        sysLog.setMethod("[类名] "+clazz.getName()+"[方法名 ]" +method.getName());
        sysLog.setUsername(username);
        sysLog.setUrl(url);
        sysLog.setVisitTime(visitTime);

       //调用service来完成将日志信息插入数据库
        sysLogService.saveSysLog(sysLog);
    }


}
