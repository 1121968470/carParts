package com.carmold.controller.mould;

import com.alibaba.fastjson.JSON;
import com.carmold.bean.ContractDetails;
import com.carmold.service.ContractDetailsService;
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
@RequestMapping("api/{version}/web/contractdetails/")
public class ContractDetailsController {

    @Autowired
    private ContractDetailsService contractDetailsService;

    /**
     * 集团皮纹收款汇总查询
     * @param pageInfo
     * @param year
     * @param typeOfMachine
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("dermatoglyph")
    public PageInfo dermatoglyph(PageInfo pageInfo, String year, String typeOfMachine,
                                  String partNumber, String customer) {
        return contractDetailsService.dermatoglyph(pageInfo, year, typeOfMachine, partNumber, customer);
    }

    /**
     * 集团设变收款汇总查询
     * @param pageInfo
     * @param year
     * @param typeOfMachine
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("assumedChange")
    public PageInfo assumedChange(PageInfo pageInfo, String year, String typeOfMachine,
                               String partNumber, String customer) {
        return contractDetailsService.assumedChange(pageInfo, year, typeOfMachine, partNumber, customer);
    }

    /**
     * 集团委外加工收款汇总查询
     * @param pageInfo
     * @param year
     * @param typeOfMachine
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("outProcess")
    public PageInfo outProcess(PageInfo pageInfo, String year, String typeOfMachine,
                             String partNumber, String customer) {
        return contractDetailsService.outProcess(pageInfo, year, typeOfMachine, partNumber, customer);
    }

    /**
     * 集团模摊收款汇总查询
     * @param pageInfo
     * @param year
     * @param typeOfMachine
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("dieStall")
    public PageInfo dieStall(PageInfo pageInfo, String year, String typeOfMachine,
                                    String partNumber, String customer) {
        return contractDetailsService.dieStall(pageInfo, year, typeOfMachine, partNumber, customer);
    }

    /**
     * 集团直接收款汇总查询
     * @param pageInfo
     * @param year
     * @param typeOfMachine
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("directSummarize")
    public PageInfo directSummarize(PageInfo pageInfo, String year, String typeOfMachine,
                                    String partNumber, String customer) {
        return contractDetailsService.directSummarize(pageInfo, year, typeOfMachine, partNumber, customer);
    }

    @GetMapping("getId")//获取id
    public CallResult getId(String id) {
        CallResult callResult = new CallResult();
        ContractDetails contractDetails = new ContractDetails();
        String[] list = id != null ? id.split(","):null;
        if(list.length>1){
            for (String i : list) {
                contractDetails = contractDetailsService.getId(i);
            }
        } else {
            contractDetails = contractDetailsService.getId(id);
        }
        return callResult.ok(contractDetails);
    }

    /**
     * 总金额查询
     * @param pageInfo
     * @param year
     * @param typeOfMachine
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("listTotal")
    public PageInfo listTotal(PageInfo pageInfo, String year, String typeOfMachine,
                              String partNumber, String customer, String contractSigningTime, String remarks) {
        return contractDetailsService.listTotal(pageInfo, year, typeOfMachine,
                partNumber, customer, contractSigningTime, remarks);
    }

    /**
     * 查询数据
     * @param pageInfo
     * @param year
     * @param typeOfMachine,
     * @param partNumber
     * @param customer
     * @return
     */
    @GetMapping("listAll")
    public PageInfo list(PageInfo pageInfo, String year, String typeOfMachine,
                         String partNumber, String customer, String contractSigningTime, String remarks) {
        return contractDetailsService.list(pageInfo, year, typeOfMachine,
                partNumber, customer, contractSigningTime, remarks);
    }

    /**
     * 插入数据
     * @param contractDetails
     * @param bindingResult
     * @return
     */
    @PostMapping("save")
    public CallResult save(@Valid @RequestBody ContractDetails contractDetails,
                           BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        CallResult callResult = new CallResult();
        if (bindingResult.hasErrors()) {
            callResult.fail(bindingResult.getFieldError().getDefaultMessage());
            return callResult;
        }
        ContractDetails cd = ObjectConverter.convert(contractDetails, ContractDetails.class);
        cd.setOperator(contractDetails.getOperator()+"："+httpServletRequest.getRemoteAddr());
        cd.setUpdateState("U");
        callResult = contractDetailsService.saveOrUpdate(cd);
        return callResult;
    }

    @GetMapping("delete")
    public CallResult del(String id, String operator, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(id)) {
            return CallResult.error("id 不能为空！");
        }
        String[] list = id != null ? id.split(","):null;
        ContractDetails cd = new ContractDetails();
        CallResult callResult = new CallResult();
        cd.setUpdateState("D");
        for (String i : list) {
            cd.setId(i);
            cd.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            callResult = contractDetailsService.saveOrUpdate(cd);
        }
        return callResult;
    }

    @PostMapping("excelExports")
    public CallResult export( @RequestBody MultipartFile file, String operator, String year,
                              HttpServletRequest httpServletRequest) throws Exception {
        CallResult callResult = new CallResult();

        Boolean bool = ImportExcel.checkFile(file);
        if (!bool) {
            callResult.succee("文件类型不正确或为空");
            return callResult;
        }

        String path = "./config/json/ContractDetails.json";

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
            ContractDetails contractDetails = JSON.parseObject(obj.toString(), ContractDetails.class);
            contractDetails.setOperator(operator+"："+httpServletRequest.getRemoteAddr());
            contractDetails.setYear(year);
            contractDetails.setUpdateState("I");
            callResult = contractDetailsService.saveOrUpdate(contractDetails);
        }

        return callResult;
    }

}
