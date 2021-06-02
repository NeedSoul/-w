package com.fh.pashopadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fh.md5.Md5Util;
import com.fh.pashopadmin.entity.UmsAdmin;
import com.fh.pashopadmin.mapper.UmsAdminMapper;
import com.fh.pashopadmin.service.IUmsAdminService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author haoxin
 * @since 2021-05-11
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Override
    public IPage<UmsAdmin> queryUmsAdminList(Page<UmsAdmin> page) {
        return umsAdminMapper.queryUmsAdminList(page);
    }

    @Override
    public void updateStatus(Long status, Long musId) {
        umsAdminMapper.updateStatus(status, musId);
    }

    @Override
    public ResultObject saveOrupdate(UmsAdmin umsAdmin) {
        if(umsAdmin.getId()==null){
            umsAdmin.setCreateTime(new Date());
            umsAdmin.setPassword(new BCryptPasswordEncoder().encode("123"));
        }
        saveOrUpdate(umsAdmin);
        return ResultObject.success();
    }

    @Override
    public UmsAdmin getUser(String userName) {
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userName);
        UmsAdmin one = getOne(wrapper);

        return one;
    }
}
