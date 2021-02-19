package com.carmold.controller.mould;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carmold.bean.Reconciliation;
import com.carmold.service.ReconciliationService;
import com.carmold.util.*;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/{version}/web/reconciliation/")
public class ReconciliationController {

    @Autowired
    private ReconciliationService reconciliationService;

    @PostMapping("save")
    public CallResult save(@Valid @RequestBody Reconciliation reconciliation,
                           BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        CallResult callResult = new CallResult();
        if (bindingResult.hasErrors()) {
            callResult.fail(bindingResult.getFieldError().getDefaultMessage());
            return callResult;
        }
        Reconciliation rec = ObjectConverter.convert(reconciliation, Reconciliation.class);
        rec.setOperator(reconciliation.getOperator()+"："+httpServletRequest.getRemoteAddr());
        rec.setUpdateState("U");
        callResult = reconciliationService.saveOrUpdate(rec);
        return callResult;
    }

    @GetMapping("delete")
    public CallResult del(String id, String operator, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(id)) {
            return CallResult.error("id 不能为空！");
        }
        String[] list = id != null ? id.split(","):null;
        Reconciliation re = new Reconciliation();
        CallResult callResult = new CallResult();
        re.setUpdateState("D");
        for (String i : list) {
            re.setId(i);
            re.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            callResult = reconciliationService.saveOrUpdate(re);
        }
        return callResult;
    }

    @GetMapping("getId")//获取id
    public CallResult getId(String id) {
        CallResult callResult = new CallResult();
        Reconciliation reconciliation = reconciliationService.getId(id);
        return callResult.ok(reconciliation);
    }

    @GetMapping("list")
    public PageInfo list(PageInfo pageInfo, String year, String month, String companyName,
                         String typeOfMachine, String customer) {
        return reconciliationService.list(pageInfo, year, month, companyName, typeOfMachine, customer);
    }

    @GetMapping("deriveExcel")
    public void deriveExcel(HttpServletResponse response, PageInfo pageInfo,
                            String year, String month, String companyName,
                                  String typeOfMachine, String customer) throws Exception {
        PageInfo rec = reconciliationService.listReport(pageInfo, year, month, companyName, typeOfMachine, customer);
        String fileName = (year!=null?year+"年":"")+(companyName!=null?companyName:"")+"对账明细";
        String jsonPath = "./config/json/Reconciliation.json";
        String jsonStr = ReadJson.readJsonFile(jsonPath);

        JSONObject jobj = JSONObject.parseObject(jsonStr);
        JSONArray data = jobj.getJSONArray("RECORDS");

        //文件名
        String sheetName = companyName!=null?companyName:"sheet";
        //sheet名

        //表头集合，作为表头参数
        List<String> titleList = new ArrayList<>();

        titleList.add("供应商");
        titleList.add("月份");
        titleList.add("日期");
        titleList.add("单号");
        titleList.add("客户");
        titleList.add("机种");
        titleList.add("模具名称");
        titleList.add("模号");
        titleList.add("品名");
        titleList.add("规格");
        titleList.add("单位");
        titleList.add("数量");
        titleList.add("工时");
        titleList.add("单价");
        titleList.add("面积");
        titleList.add("NC金额（元）");
        titleList.add("材料费");
        titleList.add("金额");
        titleList.add("备注");
        titleList.add("操作人员");

        //将对象加入到集合中，作为数据参数
        List<Object> excelJSONList = new ArrayList<>();
        for (Object obj : rec.getList()) {
            excelJSONList.add(obj);
        }

        //调取封装的方法，传入相应的参数
        DeriveExcel.createExcel(response, sheetName, titleList,
                excelJSONList, fileName);
    }

    @GetMapping("listTotal")
    public PageInfo listTotal(PageInfo pageInfo, String year, String month, String companyName,
                              String typeOfMachine, String customer) {
        return reconciliationService.listTotal(pageInfo, year, month, companyName, typeOfMachine, customer);
    }

    @PostMapping("excelExports")
    public CallResult export(@RequestBody MultipartFile file, String operator, String year, String companyName,
                             HttpServletRequest httpServletRequest) throws Exception {
        CallResult callResult = new CallResult();

        Boolean bool = ImportExcel.checkFile(file);
        if (!bool) {
            callResult.succee("文件类型不正确或为空");
            return callResult;
        }

        String path = "./config/json/Reconciliation.json";

        //工具类在下面
        HashMap<String, ArrayList> hashMap = ExcelUtils.importExcel(file, path);
        ArrayList arrayList = hashMap.get("OK");
        ArrayList arrayList2 = hashMap.get("ERROR");
        if (arrayList2 != null) {
//            Set<String> strings = hashMap.keySet();
//            String next = strings.iterator().next();
//            callResult.succee(next);
            return callResult.error(401, arrayList2.get(0).toString());
        }

        for (Object obj : arrayList) {
            Reconciliation reconciliation = JSON.parseObject(obj.toString(), Reconciliation.class);
            reconciliation.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            reconciliation.setYear(year);
            reconciliation.setCompanyName(companyName);
            reconciliation.setUpdateState("I");
            callResult = reconciliationService.saveOrUpdate(reconciliation);
        }

        return callResult;
    }

}
