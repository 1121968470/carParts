package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class DonationProjectVO {

    @JSONField(name = "end_date")
    private String endDate;

    @JSONField(name = "comment_num")
    private int commentNum;

    @JSONField(name = "target_amount")
    private int targetAmount;

    @JSONField(name = "donor_num")
    private int donorNum;

    @JSONField(name = "pv_num")
    private int pvNum;

    @JSONField(name = "received_amount")
    private double receivedAmount;

    @JSONField(name = "created_at")
    private String createdAt;

    @JSONField(name = "donee_num")
    private int doneeNum;

    @JSONField(name = "bind_id")
    private int bindId;

    @JSONField(name = "verified_at")
    private String verifiedAt;

    @JSONField(name = "intro")
    private String intro;

    @JSONField(name = "report_num")
    private int reportNum;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "donations_num")
    private int donationsNum;

    @JSONField(name = "start_date")
    private String startDate;

    @JSONField(name = "is_closed")
    private boolean isClosed;

    @JSONField(name = "is_publish")
    private boolean isPublish;

    @JSONField(name = "share_num")
    private int shareNum;

    @JSONField(name = "imported_num")
    private int importedNum;

    @JSONField(name = "type_id")
    private int typeId;

    @JSONField(name = "group_num")
    private int groupNum;

    @JSONField(name = "com_id")
    private int comId;

    @JSONField(name = "is_end")
    private boolean isEnd;

    @JSONField(name = "weight")
    private int weight;

    @JSONField(name = "expense")
    private int expense;

    @JSONField(name = "is_verified")
    private boolean isVerified;

    @JSONField(name = "is_suspend")
    private boolean isSuspend;

    @JSONField(name = "user_id")
    private int userId;

    @JSONField(name = "img_path")
    private String imgPath;

    @JSONField(name = "imported_amount")
    private int importedAmount;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "update_at")
    private String updateAt;

    @JSONField(name = "is_suspend_donate")
    private boolean isSuspendDonate;

    @JSONField(name = "progress_num")
    private int progressNum;

    @JSONField(name = "status")
    private DonationProjectStatus status;

    @Override
    public String toString() {
        return
                "Response{" +
                        "end_date = '" + endDate + '\'' +
                        ",comment_num = '" + commentNum + '\'' +
                        ",target_amount = '" + targetAmount + '\'' +
                        ",donor_num = '" + donorNum + '\'' +
                        ",pv_num = '" + pvNum + '\'' +
                        ",received_amount = '" + receivedAmount + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",donee_num = '" + doneeNum + '\'' +
                        ",bind_id = '" + bindId + '\'' +
                        ",verified_at = '" + verifiedAt + '\'' +
                        ",intro = '" + intro + '\'' +
                        ",report_num = '" + reportNum + '\'' +
                        ",id = '" + id + '\'' +
                        ",donations_num = '" + donationsNum + '\'' +
                        ",start_date = '" + startDate + '\'' +
                        ",is_closed = '" + isClosed + '\'' +
                        ",is_publish = '" + isPublish + '\'' +
                        ",share_num = '" + shareNum + '\'' +
                        ",imported_num = '" + importedNum + '\'' +
                        ",type_id = '" + typeId + '\'' +
                        ",group_num = '" + groupNum + '\'' +
                        ",com_id = '" + comId + '\'' +
                        ",is_end = '" + isEnd + '\'' +
                        ",weight = '" + weight + '\'' +
                        ",expense = '" + expense + '\'' +
                        ",is_verified = '" + isVerified + '\'' +
                        ",is_suspend = '" + isSuspend + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",img_path = '" + imgPath + '\'' +
                        ",imported_amount = '" + importedAmount + '\'' +
                        ",name = '" + name + '\'' +
                        ",update_at = '" + updateAt + '\'' +
                        ",is_suspend_donate = '" + isSuspendDonate + '\'' +
                        ",progress_num = '" + progressNum + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
