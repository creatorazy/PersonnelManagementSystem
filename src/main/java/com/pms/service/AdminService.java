package com.pms.service;

import com.pms.entity.Admin;
import java.util.List;
import java.util.Map;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
public interface AdminService extends BaseService<Admin>{


    /**
     * 登陆验证
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin queryByLoginNameAndPwd(Admin admin);

    Map<String,Object> total();

}