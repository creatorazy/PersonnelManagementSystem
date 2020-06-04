package com.pms.service.impl;

import com.pms.dao.*;
import com.pms.entity.Admin;
import com.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 11:13:04
 */
@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Resource
    private AdminDao adminDao;
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private FileDao fileDao;
    @Resource
    private NoticeDao noticeDao;
    @Resource
    private PositionDao positionDao;
    @Resource
    private StaffDao staffDao;

    @Autowired
    public void setBaseDao(AdminDao adminDao){
        super.baseDao = adminDao;
    }



    /**
     * 登陆
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin queryByLoginNameAndPwd(Admin admin) {
        return this.adminDao.queryByLoginNameAndPwd(admin);
    }

    @Override
    public Map<String, Object> total() {
        Map<String,Object> map = new HashMap<>();
        map.put("adminCount",adminDao.queryCount(null));
        map.put("depCount",departmentDao.queryCount(null));
        map.put("fileCount",fileDao.queryCount(null));
        map.put("noticeCount",noticeDao.queryCount(null));
        map.put("positionCount",positionDao.queryCount(null));
        map.put("staffCount",staffDao.queryCount(null));
        return map;
    }


}