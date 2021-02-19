package com.carmold.vo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

@Data
public class CallResultVO {
    int errno;
    String errmsg;
    ResultData data;

    public static CallResultVO fail(int errno, String errmsg) {
        CallResultVO vo = new CallResultVO();
        vo.setErrmsg(errmsg);
        vo.setErrno(errno);
        return vo;
    }

    public static CallResultVO fail(String errmsg) {
        CallResultVO vo = new CallResultVO();
        vo.setErrmsg(errmsg);
        vo.setErrno(1);
        return vo;
    }

    public static CallResultVO success() {
        CallResultVO vo = new CallResultVO();

        return vo;
    }

    public static CallResultVO success(Object data) {
        CallResultVO vo = new CallResultVO();
        ResultData resultData = new ResultData();
        resultData.setItems(data);
        vo.setData(resultData);
        return vo;
    }

    public static CallResultVO success(Object data, PageInfo pageInfo) {
        CallResultVO vo = new CallResultVO();
        ResultData resultData = new ResultData();
        resultData.setItems(data);
        resultData.setPage(pageInfo);
        vo.setData(resultData);
        return vo;
    }
}
