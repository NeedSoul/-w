package com.fh.pashopadmin.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.entity.AdminMenus;
import com.fh.pashopadmin.entity.bo.AdminMenusBo;
import com.fh.pashopadmin.mapper.AdminMenusMapper;
import com.fh.pashopadmin.service.IAdminMenusService;
import org.springframework.stereotype.Service;
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
 * @since 2021-05-17
 */
@Service
public class AdminMenusServiceImpl extends ServiceImpl<AdminMenusMapper, AdminMenus> implements IAdminMenusService {


    @Override
    public void giveMenus(AdminMenusBo bo) {
QueryWrapper<AdminMenus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",bo.getUserId());
       remove(wrapper);


        if(!CollectionUtils.isEmpty(bo.getMenusIds())){
            List<AdminMenus> menusList = new ArrayList<>();
            bo.getMenusIds().forEach(menusId->{
                AdminMenus adminMenus = new AdminMenus();
                adminMenus.setUserId(bo.getUserId());
                adminMenus.setMenusId(menusId);
                menusList.add(adminMenus);
            });
            saveBatch(menusList);
        }




    }

    @Override
    public List<Long> queryUserMenus(Long userId) {
QueryWrapper<AdminMenus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<AdminMenus> list = list(wrapper);
        List<Long> collect = list.stream().map(m -> m.getMenusId()).collect(Collectors.toList());

        return collect;
    }
}
