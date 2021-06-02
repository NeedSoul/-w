package com.fh.pashopadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.UmsAdmin;
import com.fh.result.ResultObject;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author haoxin
 * @since 2021-05-11
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    IPage<UmsAdmin> queryUmsAdminList(Page<UmsAdmin> page);

    void updateStatus(Long status, Long musId);

    ResultObject saveOrupdate(UmsAdmin umsAdmin);

    UmsAdmin getUser(String userName);
}
