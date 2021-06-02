package com.fh.pashopadmin.pmsbrand.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.pmsbrand.entity.PmsBrand;
import com.fh.result.ResultObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-20
 */
public interface IPmsBrandService extends IService<PmsBrand> {

    ResultObject saveOrupdateBrand(PmsBrand pmsBrand);

    ResultObject getBrandById(Long brandId);

    ResultObject deleteBrandById(Long brandId);
}
