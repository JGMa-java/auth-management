/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jgma.code.controller;

import com.google.code.kaptcha.Constants;
import com.jgma.code.utils.BaseRes;
import com.jgma.code.utils.RetCode;
import com.jgma.code.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.google.code.kaptcha.Constants;

/**
 * 登录相关
 */
@RestController
public class SysLoginController extends AbstractController {

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public BaseRes login(String username, String password, String captcha)throws IOException {
//        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if(!captcha.equalsIgnoreCase(kaptcha)){
//            return R.error("验证码不正确");
//        }

        try{
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);

        }catch (Exception e) {
            e.printStackTrace();
            return BaseRes.err(RetCode.RET_ERROR,"账户验证失败");
        }

        return BaseRes.ok();
    }

    /**
     * 退出
     */
    @GetMapping("logout")
    public void logout(HttpServletResponse response) throws IOException {
        ShiroUtils.logout();
        response.sendRedirect("login.html");
    }
}
