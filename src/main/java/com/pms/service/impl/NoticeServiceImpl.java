package com.pms.service.impl;

import com.pms.dao.DepartmentDao;
import com.pms.entity.Notice;
import com.pms.dao.NoticeDao;
import com.pms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Notice)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@Service("noticeService")
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements NoticeService {
    @Resource
    private NoticeDao noticeDao;

    @Autowired
    public void setBaseDao(NoticeDao noticeDao){
        super.baseDao = noticeDao;
    }

}