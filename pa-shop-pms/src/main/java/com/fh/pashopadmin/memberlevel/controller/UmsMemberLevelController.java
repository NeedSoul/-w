package com.fh.pashopadmin.memberlevel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.pashopadmin.memberlevel.entity.UmsMemberLevel;
import com.fh.pashopadmin.memberlevel.service.IUmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/memberlevel")
public class UmsMemberLevelController {

    @Autowired
    private IUmsMemberLevelService memberLevelService;

    @GetMapping
    public List<UmsMemberLevel> getMemberLevel(){
        QueryWrapper<UmsMemberLevel> wrapper = new QueryWrapper<>();
        wrapper.select("");
        return memberLevelService.list();
    }


}
