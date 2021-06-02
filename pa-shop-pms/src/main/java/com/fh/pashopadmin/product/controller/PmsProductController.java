package com.fh.pashopadmin.product.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pashopadmin.product.entity.PmsProduct;
import com.fh.pashopadmin.product.entity.ProductBo;
import com.fh.pashopadmin.product.service.IPmsProductService;
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
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private IPmsProductService service;

    @PostMapping
    public  void  saveOrUpdateProduct(@RequestBody ProductBo pmsProduct){
        System.out.println(pmsProduct.toString());
     service.saveOrUpdatesaveOrUpdateProduct(pmsProduct);
    }

    @GetMapping
    public IPage<PmsProduct> queryProductList(Page<PmsProduct> page){
        QueryWrapper<PmsProduct> queryWrapper=new QueryWrapper<PmsProduct>();
        queryWrapper.orderByDesc("id");
        return service.page(page,queryWrapper);
    }
    @DeleteMapping
    public ResultObject deleteRole(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        service.removeByIds(longs);
        return ResultObject.success();
    }
    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        PmsProduct byId = service.getById(id);
        return ResultObject.success(byId);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        service.removeById(id);
        return ResultObject.success();
    }

}
