package com.fh.pashopadmin.pmsattributecategory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pashopadmin.pmsattributecategory.entity.PmsProductAttributeCategory;
import com.fh.pashopadmin.pmsattributecategory.service.IPmsProductAttributeCategoryService;
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
@RequestMapping("/category")
public class PmsProductAttributeCategoryController {


    @Autowired
    private IPmsProductAttributeCategoryService  categoryService;
    @GetMapping
    public IPage<PmsProductAttributeCategory> queryCategoryList(Page<PmsProductAttributeCategory> page){
        return categoryService.page(page);
    }
@PostMapping
    public ResultObject saveOrUpdateCategory(@RequestBody PmsProductAttributeCategory category){
    boolean b = categoryService.saveOrUpdate(category);
    return ResultObject.success();
}

@GetMapping("{id}")
    public ResultObject  getCategoryById(@PathVariable("id") Long  id){
    PmsProductAttributeCategory category = categoryService.getById(id);
        return ResultObject.success(category);
}
@DeleteMapping("{id}")
    public  ResultObject  deleteCategory(@PathVariable("id")Long  id){
    boolean b = categoryService.removeById(id);

    return ResultObject.success();
}
    @GetMapping("select")
    public ResultObject selectAttrCeteList(){
        QueryWrapper<PmsProductAttributeCategory> queryWrapper=new QueryWrapper<PmsProductAttributeCategory>();
        queryWrapper.select("id,name");
        List<PmsProductAttributeCategory> list = categoryService.list(queryWrapper);
        return ResultObject.success(list);
    }

}
