package com.fh.pashopadmin.pmsattribute.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.pashopadmin.pmsattribute.entity.PmsProductAttribute;
import com.fh.pashopadmin.pmsattribute.entity.PmsProductAttributeSearch;
import com.fh.pashopadmin.pmsattribute.service.IPmsProductAttributeService;
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
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/attribute")
public class PmsProductAttributeController {

    @Autowired
    private IPmsProductAttributeService ipmsProductAttributeService;

    @GetMapping
    public IPage<PmsProductAttribute> queryPageAttrbute(PmsProductAttributeSearch productAttributeSearch){
        QueryWrapper<PmsProductAttribute> queryWrapper=new QueryWrapper<PmsProductAttribute>();
        queryWrapper.eq("product_attribute_category_id",productAttributeSearch.getPid());
        queryWrapper.eq("type",productAttributeSearch.getType());
        queryWrapper.orderByDesc("sort");
        return ipmsProductAttributeService.page(productAttributeSearch,queryWrapper);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        ipmsProductAttributeService.removeById(id);
        return ResultObject.success();
    }

    @PostMapping
    public ResultObject saveOrUpdateAttrbute(@RequestBody PmsProductAttribute pmsProductAttribute){
        ipmsProductAttributeService.saveOrUpdate(pmsProductAttribute);
        return  ResultObject.success();
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        PmsProductAttribute byId = ipmsProductAttributeService.getById(id);
        return ResultObject.success(byId);
    }
    @GetMapping("/{cateid}/{type}")
    public  List<PmsProductAttribute> getAttribute(@PathVariable Long cateid, @PathVariable Long type){
        QueryWrapper<PmsProductAttribute>  attributeQueryWrapper = new QueryWrapper<>();
        attributeQueryWrapper.eq("product_attribute_category_id",cateid);
        attributeQueryWrapper.eq("type",type);
        List<PmsProductAttribute> list = ipmsProductAttributeService.list(attributeQueryWrapper);

        return list;
    }


}
