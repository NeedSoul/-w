package com.fh.pashopadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.pashopadmin.entity.UmsRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author haoxin
 * @since 2021-05-12
 */
@Repository
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    List<String> getUserRoleKey(Long userid);
}
