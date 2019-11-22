package com.szk.springbootdemo.webservices;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name="UserService" ,targetNamespace = "http://webservices.springbootdemo.szk.com")
public interface UserService {


     @WebMethod//标注该方法为webservice暴露的方法,用于向外公布，它修饰的方法是webservice方法，去掉也没影响的，类似一个注释信息。
     public String getUserLoginId(@WebParam(name = "userId") String userId);

     @WebMethod
     @WebResult(name="String",targetNamespace="")
     public String getUserName(@WebParam(name = "userId") String userId);


}
