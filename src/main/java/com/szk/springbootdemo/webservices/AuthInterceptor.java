package com.szk.springbootdemo.webservices;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPException;
import java.util.Base64;

public class AuthInterceptor extends AbstractSoapInterceptor {

    private static final String BASIC_PREFIX = "Basic ";
    private static final String USERNAME = "lichmama";
    private static final String PASSWORD = "123456";

    public AuthInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            SOAPException exception = new SOAPException("auth failed, header [Authorization] not exists");
            throw new Fault(exception);
        }
        if (!auth.startsWith(BASIC_PREFIX)) {
            SOAPException exception = new SOAPException("auth failed, header [Authorization] is illegal");
            throw new Fault(exception);
        }
        String plaintext = new String(Base64.getDecoder().decode(auth.substring(BASIC_PREFIX.length())));
        if (StringUtils.isEmpty(plaintext) || !plaintext.contains(":")) {
            SOAPException exception = new SOAPException("auth failed, header [Authorization] is illegal");
            throw new Fault(exception);
        }
        String[] userAndPass = plaintext.split(":");
        String username = userAndPass[0];
        String password = userAndPass[1];
        if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
            SOAPException exception = new SOAPException("auth failed, username or password is incorrect");
            throw new Fault(exception);
        }
    }
}