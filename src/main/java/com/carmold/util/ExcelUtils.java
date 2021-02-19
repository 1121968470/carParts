package com.carmold.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExcelUtils {

    public static HashMap<String, ArrayList> importExcel(MultipartFile file,
                                                         String fileJsonPath) throws IOException {
        HashMap<String, ArrayList> hashMap = new HashMap<>();

        String jsonStr = ReadJson.readJsonFile(fileJsonPath);

        JSONObject jobj = JSONObject.parseObject(jsonStr);
        JSONArray data = jobj.getJSONArray("RECORDS");

        //获取workbook对象
        Workbook workbook = null;
        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        //根据后缀名是否excel文件
        if(filename.endsWith("xls")){
            //2003
            workbook = new HSSFWorkbook(inputStream);
        }else if(filename.endsWith("xlsx")){
            //2007
            workbook = new XSSFWorkbook(inputStream);
        }

        //创建对象，把每一行作为一个String数组，所以数组存到集合中
        ArrayList arrayList = new ArrayList<>();
        if(workbook != null){
            //循环sheet,现在是单sheet
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获取第一个sheetR
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    hashMap.put("文件sheet为空!",arrayList);
                    return hashMap;
                }
                List list = new ArrayList();

                for(int cellNum = sheet.getFirstRowNum();
                    cellNum < sheet.getRow(0).getLastCellNum(); cellNum++){
                    Cell cell = sheet.getRow(0).getCell(cellNum);//读取表中第一行
                    for (int i = 0 ; i < data.size();i++) {//表头存储
                        JSONObject json = (JSONObject) data.get(i);
                        String key = (String)json.get(getCellValue(cell));
                        list.add(key);//存储表头所对应的json文件中的字段
                    }
                }
                System.out.println(list.toString());

                //获取当前sheet开始行和结束行
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                //循环开始，除了前1行
                for(int rowNum = firstRowNum +1; rowNum <= lastRowNum; rowNum++){

                    JSONObject language = new JSONObject();

                    //获取当前行
                    Row row = sheet.getRow(rowNum);
                    //获取当前行的开始列和结束列
                    short firstCellNum = row.getFirstCellNum();
                    short lastCellNum = row.getLastCellNum();

                    //获取总行数
//                    int lastCellNum2 = row.getPhysicalNumberOfCells();
//                    String[] strings = new String[lastCellNum2];

                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++){
                        Cell cell = row.getCell(cellNum);

                        if( getCellValue(cell) != "" && list.get(cellNum) == null ){
                            arrayList.clear();
                            //将错误字段放入数组中
                            arrayList.add(sheet.getRow(0).getCell(cellNum).toString());
                            inputStream.close();
                            hashMap.put("ERROR", arrayList);
                            return hashMap;
                        }
                        if(list.get(cellNum) != null){//判断表头是否为空
                            //按表头的顺序添加当前行的数据
                            if(isMergedRegion(sheet,rowNum,cellNum)){//判断是否是合并单元格
                                int cellType=cell.getCellType();
                                //判断单元格是否为空
                                if (cellType == 3) {
                                    //获取合并单元格数据并获取判断数据的类型
                                    Cell ce = getMergedRegionValue(sheet,rowNum,cellNum);
                                    int cellType2 = ce.getCellType();
                                    if (cellType2 != 0 && cellType2 != 2) {//判断数据类型是否为数字或公式
                                        language.put(list.get(cellNum).toString(),
                                                getCellValue(ce));
                                    } else {
                                        short format = ce.getCellStyle().getDataFormat();
                                        if (CellDateFormat.isCellDateFormatted(ce) || format==58) {//判断单元格是否为日期
                                            language.put(list.get(cellNum).toString(),
                                                    getCellValue(ce));
                                        } else {
                                            language.put(list.get(cellNum).toString(),
                                                    getCellValue(cell));
                                        }
                                    }
                                } else {
                                    language.put(list.get(cellNum).toString(),
                                            getCellValue(cell));
                                }
//                                    System.out.print(cellType+"->"+cellType2+":"+
//                                            getCellValue(getMergedRegionValue(sheet,rowNum,cellNum))+"\t");
                            }else{
//                                System.out.print(list.get(cellNum).toString()+"-->:"+getCellValue(cell)+"\t");
                                language.put(list.get(cellNum).toString(),
                                        getCellValue(cell));
//                                    row = sheet.getRow(rowNum);
//                                    language.put(list.get(cellNum).toString(),
//                                            row.getCell(cellNum).toString()
//                                    );
                            }

                        }
                    }
//                    System.out.println("");
                    arrayList.add(language);

                }
            }
        }

        inputStream.close();
        hashMap.put("OK",arrayList);
        return hashMap;
    }


    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    //获取单元格各类型值，返回字符串类型
    public static String getCellValue(Cell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                short format = cell.getCellStyle().getDataFormat();
                if (CellDateFormat.isCellDateFormatted(cell) || format == 58) {
                    SimpleDateFormat sdf = null;
                    if (format == 20 || format == 32) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellValue = sdf.format(date);
                    }else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                    try {
                        cellValue = sdf.format(cell.getDateCellValue());// 日期
                    } catch (Exception e) {
                        try {
                            throw new Exception("exception on get date data!".concat(e.toString()));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }finally{
                        sdf = null;
                    }
                }  else {
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    // 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
                    cellValue = bd.toPlainString();
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cell.setCellType(CellType.STRING);
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue()+"";;
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
//                cellValue = cell.getCellFormula();
                try {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    try {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    } catch (IllegalStateException e1) {
                        cellValue = null;
                    }
                }
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "ERROR VALUE";
                break;
            default:
                cellValue = "UNKNOW VALUE";
                break;
        }
        return cellValue;
    }


    /**
     * 合并单元格处理,获取合并行
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public  List<CellRangeAddress> getCombineCell(Sheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++)
        {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }

    private  int getRowNum(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet){
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    xr = lastR;
                }
            }

        }
        return xr;

    }
    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell 存放合并单元格的list
     * @param cell 需要判断的单元格
     * @param sheet sheet
     * @return
     */
    public  String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet)
            throws Exception{
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static Cell getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return fCell ;
                }
            }
        }

        return null ;
    }


    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

}
