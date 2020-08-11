package com.jgma.code.shiro;

import com.alibaba.fastjson.JSON;
import com.jgma.code.utils.BaseRes;
import com.jgma.code.utils.RetCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
}
