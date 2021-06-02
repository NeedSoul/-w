package com.fh.pashopadmin.attributevalue.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductAttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 属性ID
     */
    private Long arrtibuteId;

    /**
     * 属性值
     */
    private String value;


}
