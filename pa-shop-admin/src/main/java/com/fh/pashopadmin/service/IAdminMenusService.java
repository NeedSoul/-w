package com.fh.pashopadmin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.AdminMenus;
import com.fh.pashopadmin.entity.bo.AdminMenusBo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-17
 */
public interface IAdminMenusService extends IService<AdminMenus> {

    void giveMenus(AdminMenusBo bo);

    List<Long> queryUserMenus(Long userId);
}
