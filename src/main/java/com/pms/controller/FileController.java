package com.pms.controller;

import com.pms.entity.File;
import com.pms.service.DepartmentService;
import com.pms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (File)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController<File>{
    /**
     * 服务对象
     */
    @Resource
    private FileService fileService;

    /**
     * 给父类注入BaseService
     * @param fileService
     */
    @Autowired
    public void setBaseService(FileService fileService){
        super.baseService = fileService;
    }

}