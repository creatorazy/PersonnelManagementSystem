package com.pms.dao;

import com.pms.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Notice)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
public interface NoticeDao extends BaseDao<Notice>{

}