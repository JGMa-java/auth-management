package com.jgma.code.controller;

import com.jgma.code.dao.SysMenuMapper;
import com.jgma.code.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCtrl {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @GetMapping("/index")
    public Object index(){
        SysMenu sysMenu = null;
        return sysMenu;
    }
}
