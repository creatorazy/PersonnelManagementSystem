package com.pms.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseService<T> {
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
     * @param offset 查询页
     * @param limit 每页条数
     * @return 对象列表
     */
    List<T> queryAllByLimit(int offset,int limit,T t);



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
     * @return 执行结果
     */
    boolean deleteByIds(List<Integer> ids);

    /**
     * 查询数据条数
     * @return 数据条数
     */
    int queryCount(T t);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param  t 实例对象
     * @return 对象列表
     */
    List<T> queryAll(T t);

    boolean isExist(String name);
}
