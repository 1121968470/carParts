package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;
import java.util.List;

@Data
@Generated("com.robohorse.robopojogenerator")
public class ProjectDetailVO {

    @JSONField(name = "target_amount")
    private int targetAmount;

    @JSONField(name = "donor_num")
    private int donorNum;

    @JSONField(name = "pv_num")
    private int pvNum;

    @JSONField(name = "is_end_byDate")
    private boolean isEndByDate;

    @JSONField(name = "donee_num")
    private int doneeNum;

    @JSONField(name = "intro")
    private String intro;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "is_publish")
    private boolean isPublish;

    @JSONField(name = "is_closed")
    private boolean isClosed;

    @JSONField(name = "share_num")
    private int shareNum;

    @JSONField(name = "settings")
    private Settings settings;

    @JSONField(name = "max_donation_amount")
    private int maxDonationAmount;

    @JSONField(name = "imports")
    private List<Object> imports;

    @JSONField(name = "account_md5sign")
    private String accountMd5sign;

    @JSONField(name = "com_id")
    private int comId;

    @JSONField(name = "weight")
    private int weight;

    @JSONField(name = "single_donation_options")
    private List<Integer> singleDonationOptions;

    @JSONField(name = "expense")
    private int expense;

    @JSONField(name = "community")
    private Community community;

    @JSONField(name = "is_suspend")
    private boolean isSuspend;

    @JSONField(name = "user_id")
    private int userId;

    @JSONField(name = "img_path")
    private String imgPath;

    @JSONField(name = "progresses")
    private List<Object> progresses;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "update_at")
    private String updateAt;

    @JSONField(name = "donors")
    private List<DonorsItem> donors;

    @JSONField(name = "uv_num")
    private int uvNum;

    @JSONField(name = "is_end_byAmount")
    private boolean isEndByAmount;

    @JSONField(name = "is_suspend_donate")
    private boolean isSuspendDonate;

    @JSONField(name = "progress_num")
    private int progressNum;

    @JSONField(name = "status")
    private Status status;

    @JSONField(name = "end_date")
    private String endDate;

    @JSONField(name = "comment_num")
    private int commentNum;

    @JSONField(name = "received_amount")
    private double receivedAmount;

    @JSONField(name = "created_at")
    private String createdAt;

    @JSONField(name = "content")
    private String content = "";

    @JSONField(name = "bind_id")
    private int bindId;

    @JSONField(name = "category_id")
    private int categoryId;

    @JSONField(name = "verified_at")
    private String verifiedAt;

    @JSONField(name = "report_num")
    private int reportNum;

    @JSONField(name = "member")
    private Member member;

    @JSONField(name = "donations_num")
    private int donationsNum;

    @JSONField(name = "start_date")
    private String startDate;

    @JSONField(name = "imported_num")
    private int importedNum;

    @JSONField(name = "comments")
    private List<Object> comments;

    @JSONField(name = "type_id")
    private int typeId;

    @JSONField(name = "group_num")
    private int groupNum;

    @JSONField(name = "is_end")
    private boolean isEnd;

    @JSONField(name = "groups")
    private List<Object> groups;

    @JSONField(name = "min_donation_amount")
    private int minDonationAmount;

    @JSONField(name = "is_verified")
    private boolean isVerified;

    @JSONField(name = "account_id")
    private int accountId;

    @JSONField(name = "imported_amount")
    private int importedAmount;

    @JSONField(name = "organizer")
    private Organizer organizer;

    @JSONField(name = "template_id")
    private int templateId;
}
