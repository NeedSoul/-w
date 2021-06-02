package com.fh.pashopadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 后台用户菜单表
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级菜单id
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 前端资源路径
     */
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private Integer sort;


}
