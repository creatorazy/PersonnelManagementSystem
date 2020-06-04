package com.pms.entity;

import java.io.Serializable;

/**
 * (AdminRole)实体类
 *
 * @author makejava
 * @since 2020-05-30 12:20:40
 */
public class AdminRole implements Serializable {
    private static final long serialVersionUID = 817148893235947881L;
    
    private Integer id;
    
    private Integer adminId;
    
    private Integer roleId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}