package com.fh.pashopadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.pashopadmin.entity.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-13
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    List<String> getUserPermissionKey(Long userid);
}
