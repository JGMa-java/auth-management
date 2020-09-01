package com.jgma.code.shiro;

import com.alibaba.fastjson.JSON;
import com.jgma.code.utils.BaseRes;
import com.jgma.code.utils.RetCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By majg on 2020-08-11
 */
public class MyShiroFilter extends UserFilter {
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse reps) throws IOException {
        HttpServletResponse response = (HttpServletResponse) reps;
        response.setContentType("application/json; charset=utf-8");//返回json
        BaseRes baseRes = new BaseRes(RetCode.RET_ERROR, "请登录！");
        response.getWriter().write(JSON.toJSONString(baseRes));
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONS
        //前后端分离项目中，由于跨域，会导致复杂请求，即会发送preflighted request，这样会导致在GET／POST等请求之前会先发一个OPTIONS请求，但OPTIONS请求并不带shiro的'Authorization'字段（shiro的Session），即OPTIONS请求不能通过shiro验证，会返回未认证的信息。
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals(RequestMethod.OPTIONS)) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        BaseRes baseRes = new BaseRes(RetCode.RET_ERROR, "请登录！");
        writer.write(JSON.toJSONString(baseRes));
        writer.close();
        return false;
//        return super.onAccessDenied(request, response);
    }


}
