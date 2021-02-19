package com.carmold.dao;

import com.carmold.bean.Reconciliation;
import com.carmold.bean.ReconciliationReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="ReconciliationDao")
public interface ReconciliationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Reconciliation record);

    int insertSelective(Reconciliation record);

    Reconciliation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Reconciliation record);

    int updateByPrimaryKey(Reconciliation record);

    List<Reconciliation> list(@Param("year") String year,
                              @Param("month") String month, @Param("companyName") String companyName,
                              @Param("typeOfMachine") String typeOfMachine, @Param("customer") String customer);

    List<Reconciliation> listTotal(@Param("year") String year,
                                   @Param("month") String month, @Param("companyName") String companyName,
                                   @Param("typeOfMachine") String typeOfMachine, @Param("customer") String customer);

    List<ReconciliationReport> listReport(@Param("year") String year,
                                          @Param("month") String month, @Param("companyName") String companyName,
                                          @Param("typeOfMachine") String typeOfMachine, @Param("customer") String customer);

}
