package com.pms.service.impl;

import com.pms.dao.DepartmentDao;
import com.pms.entity.File;
import com.pms.dao.FileDao;
import com.pms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (File)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl<File> implements FileService {
    @Resource
    private FileDao fileDao;

    @Autowired
    public void setBaseDao(FileDao fileDao){
        super.baseDao = fileDao;
    }
}