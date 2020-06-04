package com.pms.dao;

import com.pms.entity.Admin;
import com.pms.util.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    T queryById(Integer id);


    /**
     * 查询指定行数据
     *
     * @param pageUtils 分页工具类
     * @return 对象列表
     */
    List<T> queryAllByLimit(@Param("pageUtils")PageUtils pageUtils,@Param("t")T t);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param  t 实例对象
     * @return 对象列表
     */
    List<T> queryAll(T t);

    /**
     * 新增数据
     *
     * @param t 实例对象
     * @return 影响行数
     */
    int insert(T t);

    /**
     * 修改数据
     *
     * @param t 实例对象
     * @return 影响行数
     */
    int update(T t);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    int deleteByIds(@Param("ids")List<Integer> ids);

    /**
     * 查询数据条数
     * @return 数据条数
     */
    int queryCount(T t);

    int isExist(String name);
}
