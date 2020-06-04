package com.pms.service.impl;

import com.pms.dao.BaseDao;
import com.pms.service.BaseService;
import com.pms.util.PageUtils;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    protected BaseDao<T> baseDao;

    @Override
    public T queryById(Integer id) {
        return baseDao.queryById(id);
    }

    @Override
    public List<T> queryAllByLimit(int offset, int limit, T t) {
        return baseDao.queryAllByLimit(new PageUtils(offset, limit), t);
    }

    @Override
    public int insert(T t) {
        return baseDao.insert(t);
    }

    @Override
    public int update(T t) {
        return baseDao.update(t);
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        return baseDao.deleteByIds(ids) > 0;
    }

    @Override
    public int queryCount(T t) {
        return baseDao.queryCount(t);
    }

    @Override
    public List<T> queryAll(T t) {
        return baseDao.queryAll(t);
    }

    @Override
    public boolean isExist(String name) {
        return baseDao.isExist(name) > 0;
    }
}
