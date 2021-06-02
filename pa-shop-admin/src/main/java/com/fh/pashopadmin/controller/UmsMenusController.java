package com.fh.pashopadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pashopadmin.entity.UmsMenus;
import com.fh.pashopadmin.service.IUmsMenusService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户菜单表 前端控制器
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
@RestController
@RequestMapping("/menus")
public class UmsMenusController {

    @Autowired
    public IUmsMenusService MenusService;

    @GetMapping
    public IPage<UmsMenus> queryPermission(Page<UmsMenus> page, Long pid){
        QueryWrapper<UmsMenus> wrapper= new QueryWrapper<>();
        wrapper.eq("pid",pid);
        return MenusService.page(page,wrapper);
    }

    @PutMapping
    public ResultObject saveOrUpdate(@RequestBody UmsMenus permission){
        if(!ObjectUtils.isEmpty(permission.getId())){
            permission.setCreateTime(new Date());
        }

        MenusService.saveOrUpdate(permission);
        return ResultObject.success();
    }
    @GetMapping("/{id}")
    public ResultObject getPermissionById(@PathVariable("id") Long id){
        UmsMenus byId = MenusService.getById(id);
        return ResultObject.success(byId) ;
    }
    @DeleteMapping("/{id}")
    public ResultObject deletePermissionById(@PathVariable("id") Long id){
        boolean b = MenusService.removeById(id);
        return ResultObject.success();
    }

    @GetMapping("/All/{pid}")
    public List<UmsMenus> queryPermissionAll(@PathVariable("pid")Long pid){
        QueryWrapper<UmsMenus> wrapper= new QueryWrapper<>();
        if(!pid.equals(0l)){
            wrapper.notIn("pid",0);
        } else{
            wrapper.eq("pid",pid);
        }
        wrapper.select("id","name","pid");
        return MenusService.list(wrapper);
    }
    @GetMapping("/all")
    public List<Map<String,Object>> queryMenusAll(){

        return MenusService.queryMenusAll() ;
    }


}
