package com.gxa.gxaxly2021.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限
 * 表(Permission)实体类
 *
 * @author makejava
 * @since 2021-08-16 21:38:43
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = -14076121148947398L;

    private Integer permissionId;
    /**
     * 权限
     * 的名字
     */
    private String permissionName;
    /**
     * 权限的url
     */
    private String permissionUrl;
    /**
     * 1 是菜单 0 不是菜单
     */
    private Integer isMenu;
    /**
     * 权限的等
     * 级
     */
    private Integer permissionLevel;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     *排序字段
     */
    private Integer orderId;



}
