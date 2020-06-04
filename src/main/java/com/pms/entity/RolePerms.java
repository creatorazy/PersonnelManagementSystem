package com.pms.entity;

import java.io.Serializable;

/**
 * (RolePerms)实体类
 *
 * @author makejava
 * @since 2020-05-30 12:20:21
 */
public class RolePerms implements Serializable {
    private static final long serialVersionUID = -45308232968366609L;
    
    private Integer id;
    
    private Integer roleId;
    
    private Integer permsId;

    public RolePerms(Integer id, Integer roleId, Integer permsId) {
        this.id = id;
        this.roleId = roleId;
        this.permsId = permsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermsId() {
        return permsId;
    }

    public void setPermsId(Integer permsId) {
        this.permsId = permsId;
    }

}