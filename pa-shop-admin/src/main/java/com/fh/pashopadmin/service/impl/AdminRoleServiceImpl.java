package com.fh.pashopadmin.service.impl;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.entity.AdminRole;
import com.fh.pashopadmin.entity.bo.AdminRoleBo;
import com.fh.pashopadmin.mapper.AdminRoleMapper;
import com.fh.pashopadmin.service.IAdminRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-13
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Override
    @Transactional
    public void giveRole(AdminRoleBo roleBo) {
        /*删除用户的角色 */
        removeById(roleBo.getUserId());

        if(!CollectionUtils.isEmpty(roleBo.getRoleIds())){
            List<AdminRole> list = new ArrayList<>();
            roleBo.getRoleIds().forEach(roleId->{
                AdminRole adminRole = new AdminRole();
                adminRole.setRoleId(roleId);
                adminRole.setUserId(roleBo.getUserId());
                list.add(adminRole);
            });
            saveBatch(list);
        }

    }

    @Override
    public List<Long> queryUserRole(Long userId) {
        QueryWrapper<AdminRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.select("role_id");
        List<AdminRole> list = list(wrapper);
        List<Long> collect = list.stream().map(x -> x.getRoleId()).collect(Collectors.toList());

        return collect;
    }
}
