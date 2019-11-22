package com.szk.springbootdemo.webservices.impl;

import com.szk.springbootdemo.webservices.UserService;
import org.apache.cxf.interceptor.InInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Map;

@InInterceptors(interceptors={"com.szk.springbootdemo.webservices.AuthInterceptor"})
@WebService(serviceName = "UserService" ,
            targetNamespace = "http://webservices.springbootdemo.szk.com",
        endpointInterface = "com.szk.springbootdemo.webservices.UserService")
@Component
public class UserServiceImpl implements UserService {


    @Autowired
    JdbcTemplate jdbcTemplate ;

    @Override
    public String getUserLoginId(String userId) {

        String sql  = "select * from k_user where loginid = ?" ;

        Map<String,Object> m = jdbcTemplate.queryForMap(sql,userId);
        String loginId = "" ;
        if(m!=null && m.size()>0){
            loginId = (String )m.get("loginid") ;

        }
        return loginId;
    }

    @Override
    public String getUserName(String userId) {

        String sql  = "select * from k_user where loginid = ?" ;

        Map<String,Object> m = jdbcTemplate.queryForMap(sql,userId);
        String loginName = "" ;
        if(m!=null && m.size()>0){
            loginName = (String )m.get("name") ;

        }
        return loginName;
    }
}
