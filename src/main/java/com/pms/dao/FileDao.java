package com.pms.dao;

import com.pms.entity.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (File)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
public interface FileDao extends BaseDao<File>{

}