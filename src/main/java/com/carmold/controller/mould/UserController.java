package com.carmold.controller.mould;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carmold.bean.User;
import com.carmold.bean.UserPower;
import com.carmold.service.UserPowerService;
import com.carmold.util.ObjectConverter;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/{version}/web/user/")
public class UserController {

    @Autowired
    private UserPowerService userPowerService;

    @PostMapping("pwd")
    @ResponseBody
    public CallResult checkAndChange(@RequestBody String json) {
        CallResult callResult = new CallResult();
        String id = JSON.parseObject(json).getString("id");
        String userId = JSON.parseObject(json).getString("userId");
        String oldPwd = JSON.parseObject(json).getString("oldPwd");
        String password = JSON.parseObject(json).getString("password");
        User userold = userPowerService.getId(userId);
        if (userold.getPassword().equals(oldPwd)) {
            User user = new User();
            user.setPassword(password);
            user.setId(id);
            user.setLoginStatus("false");
            userPowerService.saveOrUpdate(user);
            callResult.ok("密码修改成功！");
        } else {
            return callResult.error(401,"旧密码不正确！");
        }
        return callResult;
    }

    @GetMapping("getUserPower")//获取user权限
    public PageInfo getUserPower(PageInfo pageInfo) {
        return userPowerService.getUserPower(pageInfo);
    }

    @GetMapping("list")
    public PageInfo list(PageInfo pageInfo, String userName, String loginStatus) {
        return userPowerService.list(pageInfo, userName, loginStatus);
    }

    @PostMapping("save")
    public CallResult save(@Valid @RequestBody UserPower user, BindingResult bindingResult) {
        CallResult callResult = new CallResult();
        if (bindingResult.hasErrors()) {
            callResult.fail(bindingResult.getFieldError().getDefaultMessage());
            return callResult;
        }
        UserPower us = ObjectConverter.convert(user, UserPower.class);
        callResult = userPowerService.saveOrUpdatePower(us);
        return callResult;
    }

}
