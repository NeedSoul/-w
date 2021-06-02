package com.fh.pashopadmin.pmsbrand.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.pmsbrand.entity.PmsBrand;
import com.fh.pashopadmin.pmsbrand.mapper.PmsBrandMapper;
import com.fh.pashopadmin.pmsbrand.service.IPmsBrandService;
import com.fh.result.ResultObject;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-20
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {



    @Override
    public ResultObject saveOrupdateBrand(PmsBrand pmsBrand) {
        super.saveOrUpdate(pmsBrand);
        return ResultObject.success();
    }
    @Override
    public ResultObject getBrandById(Long brandId) {
        return ResultObject.success(getById(brandId) );
    }

    @Override
    public ResultObject deleteBrandById(Long brandId) {
       removeById(brandId);
        return ResultObject.success();
    }

}
