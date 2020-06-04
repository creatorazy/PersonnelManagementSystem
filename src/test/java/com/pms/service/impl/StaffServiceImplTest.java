package com.pms.service.impl;

import com.pms.dao.StaffDao;
import com.pms.entity.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class StaffServiceImplTest {

    @Autowired
    private StaffDao staffDao;

    @Test
    public void select() {
        staffDao.queryById(1);
    }
}