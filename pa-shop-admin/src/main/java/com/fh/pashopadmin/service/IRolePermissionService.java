package com.fh.pashopadmin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.RolePermission;
import com.fh.pashopadmin.entity.bo.RolePermissionBo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
public interface IRolePermissionService extends IService<RolePermission> {

    void roleGivePermission(RolePermissionBo permissionBo);

    List<Long> queryRolePermission(Long roleId);
}
