package com.gxa.gxaxly2021.entity;

import java.io.Serializable;

/**
 * 员工
 * 角色关系表(EmplRole)实体类
 *
 * @author makejava
 * @since 2021-08-16 15:17:26
 */
public class EmplRole implements Serializable {
    private static final long serialVersionUID = -55898839032948658L;

    private Integer id;
    /**
     * 员工ID
     */
    private Integer emplId;
    /**
     * 角色的ID
     */
    private Integer roleId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public EmplRole(Integer emplId, Integer roleId) {
        this.emplId = emplId;
        this.roleId = roleId;
    }
}
