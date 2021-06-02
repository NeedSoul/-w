package com.fh.pashopadmin.controller;


import com.fh.pashopadmin.entity.bo.AdminMenusBo;
import com.fh.pashopadmin.service.IAdminMenusService;
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
 * @since 2021-05-17
 */

@RestController
@RequestMapping("/adminMenus")
public class AdminMenusController {

    @Autowired
    private IAdminMenusService menusService;

    @PutMapping
    public ResultObject  giveMenus(@RequestBody AdminMenusBo bo){
        menusService.giveMenus(bo);
        return ResultObject.success();
    }

    @GetMapping("/{userId}")
    public  ResultObject queryUserMenus(@PathVariable("userId") Long userId){
        List<Long>  list= menusService.queryUserMenus(userId);


        return ResultObject.success(list);
    }

}
