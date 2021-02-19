package com.carmold.dao;

import com.carmold.bean.ContractDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="ContractDetailsMapper")
public interface ContractDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContractDetails record);

    int insertSelective(ContractDetails record);

    ContractDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContractDetails record);

    int updateByPrimaryKey(ContractDetails record);

    List<ContractDetails> list(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                               @Param("partNumber") String partNumber, @Param("customer") String customer,
                               @Param("contractSigningTime") String contractSigningTime, @Param("remarks") String remarks);

    List<ContractDetails> listTotal(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                                    @Param("partNumber") String partNumber, @Param("customer") String customer,
                                    @Param("contractSigningTime") String contractSigningTime, @Param("remarks") String remarks);

    List<ContractDetails> directSummarize(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                                          @Param("partNumber") String partNumber, @Param("customer") String customer);

    List<ContractDetails> dieStall(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                                   @Param("partNumber") String partNumber, @Param("customer") String customer);

    List<ContractDetails> outProcess(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                                     @Param("partNumber") String partNumber, @Param("customer") String customer);

    List<ContractDetails> assumedChange(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                                        @Param("partNumber") String partNumber, @Param("customer") String customer);

    List<ContractDetails> dermatoglyph(@Param("year") String year, @Param("typeOfMachine") String typeOfMachine,
                                       @Param("partNumber") String partNumber, @Param("customer") String customer);

}
