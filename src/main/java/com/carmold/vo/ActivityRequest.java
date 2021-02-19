package com.carmold.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 活动保存页面
 *
 * @author :李志文
 * @date 2019/9/2 20:05
 */
@Data
public class ActivityRequest {
    private String id;
    @NotNull(message = "请输入标题")
    private String title;
    @NotNull(message = "请选择活动开始时间")
    private Date startTime;
    @NotNull(message = "请输入活动详情")
    private String content;
    private int total;
    private int limitNum;
    private int fee;
    private Date endTime;
    private  String cover;
    private  String address;
    private String typeId;
}
