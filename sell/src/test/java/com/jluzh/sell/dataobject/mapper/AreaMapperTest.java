package com.jluzh.sell.dataobject.mapper;

import com.jluzh.sell.dataobject.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaMapperTest {

    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void queryArea() {
        List<Area> areaList = areaMapper.queryArea();
        Assert.assertEquals(2,areaList.size());
    }

    @Test
    public void queryAreaById() {
        Area area = areaMapper.queryAreaById(1);
        Assert.assertEquals("东苑",area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area=new Area();
        area.setAreaName("南苑");
        area.setPriority(3);
        int i = areaMapper.insertArea(area);
        Assert.assertEquals(1,i);
    }

    @Test
    public void updateArea() {
        Area area = areaMapper.queryAreaById(3);
        area.setAreaName("西苑");
        area.setLastEditTime(new Date());
        int i = areaMapper.updateArea(area);
        Assert.assertEquals(1,i);

    }

    @Test
    public void deleteArea() {
        int i = areaMapper.deleteArea(3);
        Assert.assertEquals(1,i);
    }
}