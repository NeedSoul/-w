package com.fh.pashopadmin.pmscategory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.pashopadmin.pmscategory.entity.PmsProductCategory;
import com.fh.pashopadmin.pmscategory.entity.ProductCategorySearch;
import com.fh.pashopadmin.pmscategory.service.IPmsProductCategoryService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/pms/productcategory")
public class PmsProductCategoryController {


    @Autowired
    private IPmsProductCategoryService ipmsProductCategoryService;


    @GetMapping
    public IPage<PmsProductCategory> queryCateList(ProductCategorySearch productCategorySearch) {
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id", productCategorySearch.getPid());
        pmsProductCategoryQueryWrapper.orderByDesc("sort");
        return ipmsProductCategoryService.page(productCategorySearch, pmsProductCategoryQueryWrapper);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id) {
        ipmsProductCategoryService.removeById(id);
        return ResultObject.success();
    }

    @PostMapping
    public ResultObject saveOrUpdateCate(@RequestBody PmsProductCategory pmsProductCategory) {
        if (pmsProductCategory.getParentId() == 0) {
            pmsProductCategory.setLevel(0);
        } else {
            pmsProductCategory.setLevel(1);
        }
        ipmsProductCategoryService.saveOrUpdate(pmsProductCategory);
        return ResultObject.success();
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id) {
        PmsProductCategory byId = ipmsProductCategoryService.getById(id);
        return ResultObject.success(byId);
    }

    @GetMapping("select")
    public ResultObject getSelectList() {
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id", 0);
        pmsProductCategoryQueryWrapper.orderByDesc("sort");
        pmsProductCategoryQueryWrapper.select("id,name");
        List<PmsProductCategory> list = ipmsProductCategoryService.list(pmsProductCategoryQueryWrapper);
        return ResultObject.success(list);
    }
    @GetMapping("getCategory")
    public  ResultObject getCategory(){
      List<Map<String ,Object>>  list = ipmsProductCategoryService.getCategory();

        return ResultObject.success(list);
    }

}
