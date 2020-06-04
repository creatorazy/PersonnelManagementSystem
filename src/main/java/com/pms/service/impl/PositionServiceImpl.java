package com.pms.service.impl;

import com.pms.dao.DepartmentDao;
import com.pms.entity.Position;
import com.pms.dao.PositionDao;
import com.pms.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Position)表服务实现类
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
@Service("positionService")
public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService {
    @Resource
    private PositionDao positionDao;

    @Autowired
    public void setBaseDao(PositionDao positionDao){
        super.baseDao = positionDao;
    }

}