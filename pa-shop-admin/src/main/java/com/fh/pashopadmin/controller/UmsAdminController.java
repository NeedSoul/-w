package com.fh.pashopadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pashopadmin.entity.UmsAdmin;
import com.fh.pashopadmin.service.IUmsAdminService;
import com.fh.po.UserSearch;
import com.fh.result.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author haoxin
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/ums")
public class UmsAdminController {

    @Autowired
    private IUmsAdminService umsAdminService;

    @GetMapping
    @PreAuthorize("hasAnyRole('11')")
    public IPage<UmsAdmin> queryUmsAdminList(Page<UmsAdmin> page, UserSearch userSearch){
        QueryWrapper<UmsAdmin> umsAdminQueryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(userSearch.getNickName())){
            umsAdminQueryWrapper.like("nick_name",userSearch.getNickName());
        }
        umsAdminQueryWrapper.orderByDesc("create_time");
        return umsAdminService.page(page, umsAdminQueryWrapper);
    }

    @GetMapping("/{umsId}")
    public ResultObject queryUmsById(@PathVariable("umsId") Long umsId){
        return ResultObject.success(umsAdminService.getById(umsId));
    }


    @PutMapping
    public ResultObject saveOrUpdate(@RequestBody UmsAdmin umsAdmin){
        return umsAdminService.saveOrupdate(umsAdmin);
    }

    @DeleteMapping
    public ResultObject deleteBach(@RequestBody List<Long> idList){
        umsAdminService.removeByIds(idList);
        return ResultObject.success();
    }

    @DeleteMapping("/{umsId}")
    public ResultObject deleteBach(@PathVariable("umsId") Long umsId){
        umsAdminService.removeById(umsId);
        return ResultObject.success();
    }
    @GetMapping("/updateStatus")
    public ResultObject updateStatus(Long status,Long musId){
        if(status==1L){
            status=0L;
        }else if(status == 0L){
            status=1L;
        }
        umsAdminService.updateStatus(status,musId);
        return ResultObject.success();
    }

}
