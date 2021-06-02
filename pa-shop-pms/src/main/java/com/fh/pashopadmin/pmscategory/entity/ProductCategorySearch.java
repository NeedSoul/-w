package com.fh.pashopadmin.pmscategory.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class ProductCategorySearch extends Page<PmsProductCategory> {

    private Long pid;

}