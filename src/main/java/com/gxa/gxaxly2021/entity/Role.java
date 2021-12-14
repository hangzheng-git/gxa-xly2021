package com.gxa.gxaxly2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * 表(Role)实体类
 *
 * @author makejava
 * @since 2021-08-16 15:17:08
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -14707460804185281L;

    private Integer roleId;

    /**
     * 是否是超级管理员
     */
    private Integer isSuper;

    private String roleCode;
    /**
     * 角色的名
     * 字
     */
    private String roleName;
    /**
     * 部门的ID
     */
    private Integer deptId;
    /**
     * 角色的备
     * 注信息
     */
    private String roleRemark;
    /**
     * 操作人ID
     */
    private Integer operatorId;
    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    /**
     * 0 未删除， 1 删
     * 除
     */
    private Integer isDeleted;
    /**
     * 员工权限信息
     */
    private List<Permission> permissions;

    /**
     * 部门名字
     * @return
     */
    private Dept dept;
    /**
     * 操作人信息
     */
    private Empl empl;


}
