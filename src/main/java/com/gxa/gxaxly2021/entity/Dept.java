package com.gxa.gxaxly2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 组织
 * 结构表(部门表)(Dept)实体类
 *
 * @author makejava
 * @since 2021-08-14 17:01:41
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -14055265607862168L;

    private Integer deptId;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部
     * 门简称
     */
    private String deptSimpleName;
    /**
     * 部门
     * 全称
     */
    private String deptFullName;
    /**
     * 父级部门ID
     */
    private Integer parentId;
    /**
     * 部门
     * 的描述
     */
    private String deptDesc;
    /**
     * 部
     * 门备注
     */
    private String deptRemark;
    /**
     * 操作人的ID
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
     * 是否删除： 1 删除 0 未删
     * 除
     */
    private Integer isDeleted;
    /**
     * 父级部门
     */
    private Dept dept;
    /**
     * 操作人
     */
    private Empl empl;



}
