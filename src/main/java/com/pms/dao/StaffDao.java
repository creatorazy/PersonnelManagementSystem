package com.pms.dao;

import com.pms.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Staff)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-15 11:06:41
 */
public interface StaffDao extends BaseDao<Staff>{

}