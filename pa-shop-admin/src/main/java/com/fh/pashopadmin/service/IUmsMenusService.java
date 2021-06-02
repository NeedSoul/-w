package com.fh.pashopadmin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.pashopadmin.entity.UmsMenus;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户菜单表 服务类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
public interface IUmsMenusService extends IService<UmsMenus> {

    List<Map<String, Object>> queryMenusAll();
}
