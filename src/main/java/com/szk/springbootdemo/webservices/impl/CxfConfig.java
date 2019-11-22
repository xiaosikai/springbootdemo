package com.szk.springbootdemo.webservices.impl;

import com.szk.springbootdemo.webservices.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/service/*");//发布服务名称
    }

    @Bean
    public UserService userService()
    {
        return  new UserServiceImpl();
    }


    /** JAX-WS
     41      * 站点服务
     42      * **/
     @Bean(name="UserService")
     public Endpoint sweptPayEndpoint() {
         EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
         endpoint.publish("/getuser");
         return endpoint;
     }

}
