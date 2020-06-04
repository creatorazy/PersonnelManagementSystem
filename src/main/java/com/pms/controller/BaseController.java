package com.pms.controller;

import com.pms.entity.Admin;
import com.pms.entity.File;
import com.pms.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;

public class BaseController<T> {
    protected BaseService<T> baseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public T selectOne(Integer id) {
        return this.baseService.queryById(id);
    }

    /**
     * 查询数据数量
     * @return 数据数量
     */
    @GetMapping("/count")
    public int selectCount(T t){
        return baseService.queryCount(t);
    }
}
