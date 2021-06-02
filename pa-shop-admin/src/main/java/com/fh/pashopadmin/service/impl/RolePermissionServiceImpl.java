package com.fh.pashopadmin.service.impl;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.entity.RolePermission;
import com.fh.pashopadmin.entity.bo.RolePermissionBo;
import com.fh.pashopadmin.mapper.RolePermissionMapper;
import com.fh.pashopadmin.service.IRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Override
    public void roleGivePermission(RolePermissionBo permissionBo) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",permissionBo.getRoleId());
        remove(wrapper);

        List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        permissionBo.getPermissionIds().forEach(permission->{
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(permissionBo.getRoleId());
            rolePermission.setPermissionId(permission );
            rolePermissions.add(rolePermission);
        });
        boolean b = saveBatch(rolePermissions);
    }

    @Override
    public List<Long> queryRolePermission(Long roleId) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);

        List<RolePermission> list = list(wrapper);
        List<Long> collect = list.stream().map(p -> p.getPermissionId()).collect(Collectors.toList());

        return collect;
    }
}
