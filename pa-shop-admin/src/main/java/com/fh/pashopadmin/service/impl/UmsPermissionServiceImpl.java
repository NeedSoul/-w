package com.fh.pashopadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.entity.UmsPermission;
import com.fh.pashopadmin.mapper.UmsPermissionMapper;
import com.fh.pashopadmin.service.IUmsPermissionService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-13
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements IUmsPermissionService {

    @Autowired
    private  UmsPermissionMapper permissionMapper;

    @Override
    public List<String> getUserPermissionKey(Long userid) {

        return permissionMapper.getUserPermissionKey(userid);
    }
}
