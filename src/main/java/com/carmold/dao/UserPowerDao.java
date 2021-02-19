package com.carmold.dao;

import com.carmold.bean.UserPower;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="UserPowerDao")
public interface UserPowerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPower record);

    int insertSelective(UserPower record);

    UserPower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPower record);

    int updateByPrimaryKey(UserPower record);

    List<UserPower> list();
}