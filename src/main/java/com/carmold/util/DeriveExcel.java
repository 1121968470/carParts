package com.carmold.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class DeriveExcel {

    /**
     *  生成Excel表格
     * @param sheetName sheet名称
     * @param titleList 表头列表
     * @param dataList 数据列表
     * @param fileName 文件名
     * @return HSSFWorkbook对象
     * */
    public static void createExcel(HttpServletResponse response, String sheetName,
                                           List <String> titleList,
                                           List dataList, String fileName){
        try {
            //创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建sheet对象
            HSSFSheet sheet = wb.createSheet(sheetName);
            //在sheet里创建第一行，这里即是表头
            HSSFRow rowTitle=sheet.createRow(0);

            //写入表头的每一个列
            for (int i = 0; i < titleList.size(); i++) {
                //创建单元格
                rowTitle.createCell(i).setCellValue(titleList.get(i));
            }

            //写入每一行的记录
            for (int i = 0; i < dataList.size(); i++) {
                //创建新的一行，递增
                HSSFRow rowData = sheet.createRow(i+1);

                //通过反射，获取POJO对象
                Class cl = dataList.get(i).getClass();
                //获取类的所有字段
                Field[] fields = cl.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    //设置字段可见，否则会报错，禁止访问
                    fields[j].setAccessible(true);
                    //创建单元格
                    rowData.createCell(j).setCellValue((String) fields[j].get(dataList.get(i)));
                }
            }

            //输出Excel文件
            OutputStream output=response.getOutputStream();
            response.reset();
            //中文名称要进行编码处理
            response.setHeader("Content-disposition",
                    "attachment; filename=" +
                            new String(fileName.getBytes("GB2312"),
                                    "ISO8859-1")+".xlsx");
            response.setContentType("application/x-xls");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
