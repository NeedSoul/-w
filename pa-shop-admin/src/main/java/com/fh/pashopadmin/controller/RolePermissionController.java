package com.fh.pashopadmin.controller;


import com.fh.pashopadmin.entity.bo.RolePermissionBo;

import com.fh.pashopadmin.service.IRolePermissionService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
@RestController
@RequestMapping("/rolepermission")
public class RolePermissionController {

    @Autowired
    private IRolePermissionService rolePermissionService;

    @PutMapping
    public ResultObject roleGivePermission(@RequestBody RolePermissionBo permissionBo){
        rolePermissionService.roleGivePermission(permissionBo);

        return ResultObject.success();
    }
            @GetMapping("{roleId}")
    public List<Long> queryRolePermission(@PathVariable("roleId")Long RoleId){


                return rolePermissionService.queryRolePermission(RoleId);
            }
}
