package com.fh.pashopadmin.controller;


import com.fh.pashopadmin.entity.bo.AdminRoleBo;
import com.fh.pashopadmin.service.IAdminRoleService;
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
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/adminrole")
public class AdminRoleController {

    @Autowired
    private IAdminRoleService roleService;

    @PutMapping
    public ResultObject giveRole(@RequestBody AdminRoleBo roleBo){
        roleService.giveRole(roleBo);
        return ResultObject.success();
    }

    @GetMapping("/{userId}")
    public ResultObject queryUserRole(@PathVariable("userId") Long userId){
       List<Long> list = roleService.queryUserRole(userId);
       return ResultObject.success(list);
    }


}
