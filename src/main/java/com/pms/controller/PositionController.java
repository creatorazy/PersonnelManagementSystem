package com.pms.controller;

import com.pms.entity.Position;
import com.pms.service.DepartmentService;
import com.pms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Position)表控制层
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@RestController
@RequestMapping("/position")
public class PositionController extends BaseController<Position>{
    /**
     * 服务对象
     */
    @Resource
    private PositionService positionService;
    /**
     * 给父类注入BaseService
     * @param positionService
     */
    @Autowired
    public void setBaseService(PositionService positionService){
        super.baseService = positionService;
    }

}