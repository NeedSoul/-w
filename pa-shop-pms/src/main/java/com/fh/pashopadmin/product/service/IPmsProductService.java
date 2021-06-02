package com.fh.pashopadmin.product.service;

import com.fh.pashopadmin.product.entity.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.product.entity.ProductBo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-23
 */
public interface IPmsProductService extends IService<PmsProduct> {

    void saveOrUpdatesaveOrUpdateProduct(ProductBo pmsProduct);
}
