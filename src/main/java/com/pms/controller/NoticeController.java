package com.pms.controller;

import com.pms.entity.Notice;
import com.pms.service.DepartmentService;
import com.pms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Notice)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController<Notice>{
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    /**
     * 给父类注入BaseService
     * @param noticeService
     */
    @Autowired
    public void setBaseService(NoticeService noticeService){
        super.baseService = noticeService;
    }

}