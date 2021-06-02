package com.fh.pashopadmin.pmsbrand.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fh.pashopadmin.pmsbrand.entity.BrandBo;
import com.fh.pashopadmin.pmsbrand.entity.PmsBrand;
import com.fh.pashopadmin.pmsbrand.service.IPmsBrandService;
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
 * @since 2021-05-20
 */
@RestController
@RequestMapping("/brands")
public class PmsBrandController {

    @Autowired
    private IPmsBrandService brandService;

    @GetMapping
    public ResultObject queryBrandList(BrandBo brandBo){
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(brandBo.getName())){
            queryWrapper.like("name",'%'+brandBo.getName()+'%');
        }
        return ResultObject.success(brandService.page(brandBo,queryWrapper));
    }
    @PostMapping
    public ResultObject saveOrupdateBrand(@RequestBody PmsBrand pmsBrand){
        return brandService.saveOrupdateBrand(pmsBrand);
    }
    @GetMapping("/{brandId}")
    public ResultObject getBrandById(@PathVariable("brandId") Long brandId){
        return brandService.getBrandById(brandId);
    }
    @DeleteMapping("/{brandId}")
    public ResultObject deleteBrandById(@PathVariable("brandId") Long brandId){
        return brandService.deleteBrandById(brandId);
    }
    @GetMapping("/list")
    public List<PmsBrand> getBrandList(){
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.select("id","name");
    return brandService.list(wrapper);
}

}
