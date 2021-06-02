package com.fh.pashopadmin.pmscategory.service;

import com.fh.pashopadmin.pmscategory.entity.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-23
 */
public interface IPmsProductCategoryService extends IService<PmsProductCategory> {

    List<Map<String, Object>> getCategory();
}
