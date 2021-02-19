package com.carmold.controller.mould;


import com.alibaba.fastjson.JSON;
import com.carmold.bean.ChangeDevelopmentList;
import com.carmold.bean.DevelopmentList;
import com.carmold.service.ChangeDevelopmentListService;
import com.carmold.service.DevelopmentListService;
import com.carmold.util.ExcelUtils;
import com.carmold.util.ImportExcel;
import com.carmold.util.ObjectConverter;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/{version}/web/developmentlist/")
public class DevelopmentListController {
    @Autowired
    private DevelopmentListService developmentListService;

    @Autowired
    private ChangeDevelopmentListService changeDevelopmentListService;

    /**
     * 查询数据
     * @param pageInfo
     * @param year
     * @param customer
     * @return
     */
    @GetMapping("listAll")//制模数据查询
    public PageInfo list(PageInfo pageInfo, String year, String model, String itemId, String customer) {
        return developmentListService.list(pageInfo, year, model, itemId, customer);
    }

    @PostMapping("saveChange")
    public CallResult saveChange(@Valid @RequestBody ChangeDevelopmentList developmentList,
                                 BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        CallResult callResult = new CallResult();
        if (bindingResult.hasErrors()) {
            callResult.fail(bindingResult.getFieldError().getDefaultMessage());
            return callResult;
        }
        ChangeDevelopmentList development = ObjectConverter.convert(developmentList, ChangeDevelopmentList.class);
        development.setOperator(developmentList.getOperator()+"："+httpServletRequest.getRemoteAddr());
        development.setUpdateState("U");
        callResult = changeDevelopmentListService.saveOrUpdate(development);
        return callResult;
    }

    @GetMapping("getIdChange")//获取id
    public CallResult getIdChange(String id) {
        CallResult callResult = new CallResult();
        ChangeDevelopmentList developmentList = changeDevelopmentListService.getId(id);
        return callResult.ok(developmentList);
    }

    @GetMapping("deleteChange")
    public CallResult delChange(String id, String operator, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(id)) {
            return CallResult.error("id 不能为空！");
        }
        String[] list = id != null ? id.split(","):null;
        ChangeDevelopmentList dl = new ChangeDevelopmentList();
        CallResult callResult = new CallResult();
        dl.setUpdateState("D");
        for (String i : list) {
            dl.setId(i);
            dl.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            callResult = changeDevelopmentListService.saveOrUpdate(dl);
        }
        return callResult;
    }

    @GetMapping("changeListAll")//修模或设变数据查询
    public PageInfo changeList(PageInfo pageInfo,String type, String year, String model,
                               String itemId, String customer, String repairOrChange) {
        return changeDevelopmentListService.list(pageInfo, type, year, model, itemId, customer, repairOrChange);
    }

    @GetMapping("getId")//获取id
    public CallResult getId(String id) {
        CallResult callResult = new CallResult();
        DevelopmentList developmentList = developmentListService.getId(id);
        return callResult.ok(developmentList);
    }

    @GetMapping("delete")
    public CallResult del(String id, String operator, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(id)) {
            return CallResult.error("id 不能为空！");
        }
        String[] list = id != null ? id.split(","):null;
        DevelopmentList dl = new DevelopmentList();
        CallResult callResult = new CallResult();
        dl.setUpdateState("D");
        for (String i : list) {
            dl.setId(i);
            dl.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            callResult = developmentListService.saveOrUpdate(dl);
        }
        return callResult;
    }

    /**
     * 保存数据
     * @param developmentList
     * @param bindingResult
     * @return
     */
    @PostMapping("save")
    public CallResult save(@Valid @RequestBody DevelopmentList developmentList,
                           BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        CallResult callResult = new CallResult();
        if (bindingResult.hasErrors()) {
            callResult.fail(bindingResult.getFieldError().getDefaultMessage());
            return callResult;
        }
        DevelopmentList development = ObjectConverter.convert(developmentList, DevelopmentList.class);
        development.setOperator(developmentList.getOperator()+"："+httpServletRequest.getRemoteAddr());
        development.setUpdateState("U");
        callResult = developmentListService.saveOrUpdate(development);
        return callResult;
    }

    @PostMapping("excelExports")//制模数据导入
    public CallResult export(@RequestBody MultipartFile file, String year, String operator,
                             HttpServletRequest httpServletRequest) throws Exception{
        CallResult callResult = new CallResult();

        Boolean bool = ImportExcel.checkFile(file);
        if(!bool){
            callResult.succee("文件类型不正确或为空");
            return callResult;
        }

        String path = "./config/json/DevelopmentList.json";

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
            DevelopmentList development = JSON.parseObject(obj.toString(), DevelopmentList.class);
            development.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            development.setYear(year);
            development.setUpdateState("I");
            callResult = developmentListService.saveOrUpdate(development);
        }

        return callResult;

    }

    @PostMapping("changeExports")//设变或修模数据导入
    public CallResult changeExport( @RequestBody MultipartFile file, String year, String operator,
                                    String repairOrChange, HttpServletRequest httpServletRequest) throws Exception{
        CallResult callResult = new CallResult();

        Boolean bool = ImportExcel.checkFile(file);
        if(!bool){
            callResult.succee("文件类型不正确或为空");
            return callResult;
        }

        String path = "./config/json/ChangeDevelopmentList.json";

        //工具类在下面
        HashMap<String, ArrayList> hashMap = ExcelUtils.importExcel(file, path);
        ArrayList arrayList = hashMap.get("OK");
        ArrayList arrayList2 = hashMap.get("ERROR");
        if (arrayList2 != null) {
            return callResult.error(401, arrayList2.get(0).toString());
        }

        for (Object obj : arrayList) {
            ChangeDevelopmentList development = JSON.parseObject(obj.toString(), ChangeDevelopmentList.class);
            development.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            development.setYear(year);
            development.setRepairOrChange(repairOrChange);
            development.setUpdateState("I");
            callResult = changeDevelopmentListService.saveOrUpdate(development);
        }

        return callResult;

    }

}
