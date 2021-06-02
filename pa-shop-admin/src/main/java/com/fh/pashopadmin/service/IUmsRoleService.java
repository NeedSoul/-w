package com.fh.pashopadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.UmsRole;
import com.fh.result.ResultObject;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author haoxin
 * @since 2021-05-12
 */
public interface IUmsRoleService extends IService<UmsRole> {

    ResultObject saveOrupdate(UmsRole umsRole);

    List<String> getUserRoleKey(Long userid);
}
