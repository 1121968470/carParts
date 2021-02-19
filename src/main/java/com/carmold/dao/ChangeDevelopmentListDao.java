package com.carmold.dao;

import com.carmold.bean.ChangeDevelopmentList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="ChangeDevelopmentListDao")
public interface ChangeDevelopmentListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ChangeDevelopmentList record);

    int insertSelective(ChangeDevelopmentList record);

    ChangeDevelopmentList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChangeDevelopmentList record);

    int updateByPrimaryKey(ChangeDevelopmentList record);

    List<ChangeDevelopmentList> list(@Param("type") String type, @Param("year") String year,
                                     @Param("model") String model, @Param("itemId") String itemId,
                                     @Param("customer") String customer, @Param("repairOrChange") String repairOrChange);

}