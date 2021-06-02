package com.fh.pashopadmin.pmscategory.service.impl;

import com.fh.pashopadmin.pmscategory.entity.PmsProductCategory;
import com.fh.pashopadmin.pmscategory.mapper.PmsProductCategoryMapper;
import com.fh.pashopadmin.pmscategory.service.IPmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-23
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements IPmsProductCategoryService {

    @Override
    public List<Map<String, Object>> getCategory() {
        List<PmsProductCategory> list = list();
        return listMap(list,0l);
    }

    public  List<Map<String, Object>> listMap(List<PmsProductCategory> list ,Long  pid ){
        List<Map<String, Object>>  Maplist= new ArrayList<Map<String, Object>>();
        list.forEach(PP->{
             Map<String, Object>  map = null;
            if (pid.equals(PP.getParentId())){
                map= new HashMap<>();
                map.put("value",PP.getId());
                map.put("label",PP.getName());
                map.put("children",listMap(list,PP.getId()));

            }
            if (map!=null){
                Maplist.add(map);
            }
        });

        return Maplist;
    }

}
