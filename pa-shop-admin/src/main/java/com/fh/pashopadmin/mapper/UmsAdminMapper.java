package com.fh.pashopadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.fh.pashopadmin.entity.UmsAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author haoxin
 * @since 2021-05-11
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    IPage<UmsAdmin> queryUmsAdminList(Page<UmsAdmin> page);

    void updateStatus(@Param("status") Long status,@Param("musId") Long musId);
}
