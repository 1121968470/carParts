package com.carmold.controller.mould;

import com.carmold.bean.User;
import com.carmold.service.UserPowerService;
import com.carmold.util.ObjectConverter;
import com.carmold.vo.CallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/{version}/web/account/")
public class AccountController {

    @Autowired
    private UserPowerService userPowerService;

    @GetMapping("login")
    public CallResult getId( String userId, String password, HttpServletRequest httpServletRequest) {
        CallResult callResult = new CallResult();
        User user = userPowerService.getId(userId);
        if (user==null) {
            return callResult.error(401,"用户名或密码不正确！");
        }
        System.out.println(httpServletRequest.getRemoteAddr()+"："+password+"-"+user.getPassword());
        if (!password.equals(user.getPassword())) {
            return callResult.error(401,"用户名或密码不正确！");
        }
        return callResult.ok(user);
    }

    @PostMapping("save")
    public CallResult save(@Valid @RequestBody User user, BindingResult bindingResult) {
        CallResult callResult = new CallResult();
        if (bindingResult.hasErrors()) {
            callResult.fail(bindingResult.getFieldError().getDefaultMessage());
            return callResult;
        }
        User us = ObjectConverter.convert(user, User.class);
        callResult = userPowerService.saveOrUpdate(us);
        return callResult;
    }

}
