package com.fh.pashopadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pashopadmin.entity.UmsRole;
import com.fh.pashopadmin.service.IUmsRoleService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author haoxin
 * @since 2021-05-12
 */
@RestController
@RequestMapping("/roles")
public class UmsRoleController {

    @Autowired
    private IUmsRoleService umsRoleService;

    @GetMapping
    public IPage<UmsRole> queryUmsRoleList(Page<UmsRole> page){
        return umsRoleService.page(page);
    }

    @GetMapping("/{roleId}")
    public ResultObject getUmsRileById(@PathVariable("roleId") Long roleId){
        return ResultObject.success(umsRoleService.getById(roleId));
    }

    @PutMapping
    public ResultObject saveOrUpdateUmsRole(@RequestBody UmsRole umsRole){
        return umsRoleService.saveOrupdate(umsRole);
    }

    @DeleteMapping
    public ResultObject deleteBach(@RequestBody List<Long> roleList){
        umsRoleService.removeByIds(roleList);
        return ResultObject.success();
    }
    @DeleteMapping("/{roleId}")
    public ResultObject deteleRole(@PathVariable("roleId") Long roleId){
        umsRoleService.removeById(roleId);
        return ResultObject.success();
    }
    @GetMapping("All")
    public  ResultObject queryRoleAll(){
        QueryWrapper<UmsRole>  wrapper = new QueryWrapper<>();
        wrapper.select("id","name");
        List<UmsRole> list = umsRoleService.list(wrapper);
        return ResultObject.success(list);
    }

}
