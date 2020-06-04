package com.pms.dao;

import com.pms.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
public interface AdminDao extends BaseDao<Admin> {
    /**
     * 通过登陆账户和密码查询单条数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin queryByLoginNameAndPwd(Admin admin);

    Admin queryByLoginName(String name);

}