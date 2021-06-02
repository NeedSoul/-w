package com.fh.pashopadmin.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionBo {
    private  Long  roleId;
    private List<Long>  permissionIds;


}
