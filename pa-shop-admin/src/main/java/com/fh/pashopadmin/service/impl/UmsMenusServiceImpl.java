package com.fh.pashopadmin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.entity.UmsMenus;
import com.fh.pashopadmin.mapper.UmsMenusMapper;
import com.fh.pashopadmin.service.IUmsMenusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户菜单表 服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-15
 */
@Service
public class UmsMenusServiceImpl extends ServiceImpl<UmsMenusMapper, UmsMenus> implements IUmsMenusService {

    @Override
    public List<Map<String, Object>> queryMenusAll() {

        List<UmsMenus> list = list();

        return getTree(list,0l);
    }

    private List<Map<String, Object>> getTree(List<UmsMenus> list, long pid) {
        List<Map<String,Object>>  treeList = new ArrayList<>();

        list.forEach(menu->{
            Map<String, Object>  map = null;

            if (menu.getPid().equals(pid)){
                map  = new HashMap<String ,Object>();
                map.put("id",menu.getId());
                map.put("label",menu.getName());
                map.put("children",getTree(list,menu.getId()));
            }

            if(  map != null){

                treeList.add(map);
            }

        });


        return treeList;
    }


}
