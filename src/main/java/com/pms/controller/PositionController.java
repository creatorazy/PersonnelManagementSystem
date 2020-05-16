package com.pms.controller;

import com.pms.entity.Position;
import com.pms.service.PositionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Position)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@RestController
@RequestMapping("position")
public class PositionController {
    /**
     * 服务对象
     */
    @Resource
    private PositionService positionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Position selectOne(Integer id) {
        return this.positionService.queryById(id);
    }

    /**
     * 查询数据数量
     * @return 数据数量
     */
    @GetMapping("/count")
    public int selectCount(){
        return positionService.queryCount();
    }

}