package com.fh.pashopadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.entity.UmsRole;
import com.fh.pashopadmin.mapper.UmsRoleMapper;
import com.fh.pashopadmin.service.IUmsRoleService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author haoxin
 * @since 2021-05-12
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Override
    public ResultObject saveOrupdate(UmsRole umsRole) {
        if(umsRole.getId()==null){
            umsRole.setCreateTime(new Date());
        }
        saveOrUpdate(umsRole);
        return ResultObject.success();
    }

    @Override
    public List<String> getUserRoleKey(Long userid) {
        return umsRoleMapper.getUserRoleKey(userid);
    }
}
