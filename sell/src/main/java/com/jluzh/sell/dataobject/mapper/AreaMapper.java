package com.jluzh.sell.dataobject.mapper;
import com.jluzh.sell.dataobject.Area;

import java.util.List;

public interface AreaMapper {

    List<Area> queryArea();

    Area queryAreaById(int areaId);

    int insertArea(Area area);

    int updateArea(Area area);

    int deleteArea(int areaId);
}
