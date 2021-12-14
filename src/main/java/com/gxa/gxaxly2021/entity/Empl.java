package com.gxa.gxaxly2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 员工
 * 表(Empl)实体类
 *
 * @author makejava
 * @since 2021-08-15 21:48:32
 */
@Data
public class Empl implements Serializable {
    private static final long serialVersionUID = 334368359770380410L;

    private Integer emplId;
    /**
     * 员工编码
     */
    private String emplCode;
    /**
     * 员工姓名
     */
    private String emplName;
    /**
     * 员工登录
     * 账号
     */
    private String emplAccount;
    /**
     * 员工的登录密码
     */
    private String emplPwd;
    /**
     * 手机号
     */
    private Long emplPhone;
    /**
     * 性别： 1 男 2
     * 女
     */
    private Integer emplGender;
    /**
     * 状态 1 正常 0
     * 禁用
     */
    private Integer emplStatus;
    /**
     * 所属部门ID
     */
    private Integer deptId;
    /**
     * 操作人ID
     */
    private Integer operatorId;
    /**
     * 备
     * 注信息
     */
    private String emplRemark;
    /**
     * 最后登录时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp lastLoginTime;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;

    private Integer isDeleted;
    private Integer canErrorNum;

    /**
     * 员工所属部门
     */
    private Dept dept;

    /**
     * 员工角色信息
     */
    private List<Role> roles;




    public Empl() {
    }

    public Empl(Integer emplId, Integer canErrorNum) {
        this.emplId = emplId;
        this.canErrorNum = canErrorNum;
    }

    public Empl(Integer emplId, Timestamp lastLoginTime) {
        this.emplId = emplId;
        this.lastLoginTime = lastLoginTime;
    }
}
