package com.fh.pashopadmin.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class AdminRoleBo {

    private Long  userId;
    public List<Long>   roleIds;
}
