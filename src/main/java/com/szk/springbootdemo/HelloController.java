package com.szk.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


@Controller
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate ;

    @RequestMapping("/userList")
    public String hello(HttpServletRequest request){
        String sql  = "select * from k_user " ;


        List<Map<String,Object>> l = jdbcTemplate.queryForList(sql);

        request.setAttribute("l",l);
        return "user/userList" ;
    }

    @RequestMapping(value = "/user/{id}" ,params = {"id!=admin"})
    public String user(ModelMap map, @PathVariable String id){

        String sql  = "select * from k_user where loginid = ?" ;

        Map<String,Object> m = jdbcTemplate.queryForMap(sql,id);

        map.addAttribute("user",m) ;
        return "user/user" ;
    }


}
