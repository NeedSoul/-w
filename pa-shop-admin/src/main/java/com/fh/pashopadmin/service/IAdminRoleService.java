package com.fh.pashopadmin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.AdminRole;
import com.fh.pashopadmin.entity.bo.AdminRoleBo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-13
 */
public interface IAdminRoleService extends IService<AdminRole> {

    void giveRole(AdminRoleBo roleBo);

    List<Long> queryUserRole(Long userId);
}
