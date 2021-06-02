package com.fh.pashopadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-13
 */
public interface IUmsPermissionService extends IService<UmsPermission> {

    List<String> getUserPermissionKey(Long userid);
}
