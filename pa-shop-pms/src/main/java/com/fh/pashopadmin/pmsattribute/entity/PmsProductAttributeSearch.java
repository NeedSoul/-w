package com.fh.pashopadmin.pmsattribute.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PmsProductAttributeSearch extends Page<PmsProductAttribute> {

    private Long pid;
    private Integer  type;

}