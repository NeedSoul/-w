package com.fh.pashopadmin.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class AdminMenusBo {
    private Long  userId;
    private List<Long> menusIds;

}
