package com.fh.pashopadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pashopadmin.entity.UmsPermission;
import com.fh.pashopadmin.service.IUmsPermissionService;
import com.fh.pashopadmin.service.impl.UmsPermissionServiceImpl;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 后台用户权限表 前端控制器
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/permission")
public class UmsPermissionController {

    @Autowired
    public IUmsPermissionService permissionService;

  @GetMapping
    public IPage<UmsPermission> queryPermission(Page<UmsPermission> page,Long pid){
    QueryWrapper<UmsPermission> wrapper= new QueryWrapper<>();
    wrapper.eq("pid",pid);
    return permissionService.page(page,wrapper);
  }

  @PutMapping
    public ResultObject saveOrUpdate(@RequestBody UmsPermission permission){
    if(!ObjectUtils.isEmpty(permission.getId())){
      permission.setCreateTime(new Date());
    }

      permissionService.saveOrUpdate(permission);
      return ResultObject.success();
  }
  @GetMapping("/{id}")
    public ResultObject getPermissionById(@PathVariable("id") Long id){
      UmsPermission byId = permissionService.getById(id);
      return ResultObject.success(byId) ;
  }
  @DeleteMapping("/{id}")
    public ResultObject deletePermissionById(@PathVariable("id") Long id){
      boolean b = permissionService.removeById(id);
      return ResultObject.success();
  }

  @GetMapping("/All/{pid}")
  public List<UmsPermission> queryPermissionAll(@PathVariable("pid")Long pid){
    QueryWrapper<UmsPermission> wrapper= new QueryWrapper<>();
    if(!pid.equals(0l)){
      wrapper.notIn("pid",0);
    } else{
      wrapper.eq("pid",pid);
    }
    wrapper.select("id","name","pid");
    return permissionService.list(wrapper);
  }

}
