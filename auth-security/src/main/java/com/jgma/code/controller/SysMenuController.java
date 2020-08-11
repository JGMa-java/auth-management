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

import com.jgma.code.entity.SysMenuEntity;
import com.jgma.code.service.SysMenuService;
import com.jgma.code.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.jgma.code.utils.Constant.MenuType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author 知秋
 * @email fei6751803@163.com
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController{

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 用户进入主页 菜单列表
     */
    @RequestMapping("/user")
    public BaseRes user(){
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());

        return BaseRes.ok().put( menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public BaseRes list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysMenuEntity> menuList = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());

        return BaseRes.ok().put( pageUtil);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu){
        if(StringUtils.isEmpty(menu.getName())){
            throw new RRException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == Constant.MenuType.MENU.getValue()){
            if(StringUtils.isEmpty(menu.getUrl())){
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == MenuType.CATALOG.getValue() ||
                menu.getType() == MenuType.MENU.getValue()){
            if(parentType != MenuType.CATALOG.getValue()){
                throw new RRException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == MenuType.BUTTON.getValue()){
            if(parentType != MenuType.MENU.getValue()){
                throw new RRException("上级菜单只能为菜单类型");
            }
        }
    }
}
