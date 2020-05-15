package com.pms.controller;

import com.pms.entity.File;
import com.pms.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (File)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@RestController
@RequestMapping("file")
public class FileController {
    /**
     * 服务对象
     */
    @Resource
    private FileService fileService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public File selectOne(Integer id) {
        return this.fileService.queryById(id);
    }

}