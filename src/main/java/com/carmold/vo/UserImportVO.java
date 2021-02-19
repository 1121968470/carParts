package com.carmold.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 校友对象导入对象
 *
 * @author :李志文
 * @date 2019/8/31 20:27
 */
@Data
public class UserImportVO {
    @Excel(name = "名字", isImportField = "true_st", orderNum = "0")
    private String name;
    @Excel(name = "毕业年份", isImportField = "true_st", orderNum = "1")
    private String year;
    @Excel(name = "毕业班级", isImportField = "true_st", orderNum = "2")
    private String className;
    @Excel(name = "毕业班主任", isImportField = "true_st", orderNum = "3")
    private String headTeacher;


}
