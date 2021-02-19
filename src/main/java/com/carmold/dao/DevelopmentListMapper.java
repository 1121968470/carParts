package com.carmold.dao;

import com.carmold.bean.DevelopmentList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="DevelopmentListMapper")
public interface DevelopmentListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DevelopmentList developmentList);

    int insertSelective(DevelopmentList developmentList);

    DevelopmentList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DevelopmentList developmentList);

    int updateByPrimaryKey(DevelopmentList developmentList);

    List<DevelopmentList> list(@Param("year") String year, @Param("model") String model, @Param("itemId") String itemId, @Param("customer") String customer);
}
