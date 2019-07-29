package com.qm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author: liqm
 * 2019-07-23
 */
@RequestMapping(value = "/user")
@Controller
@Slf4j
public class UserController {

    @ResponseBody
    @PostMapping(value = "/getUser")
    public String getUser(){

        log.info("获取用户信息!");

        return "获取成功... 😃";
    }

    @ResponseBody
    @PostMapping(value = "/updateUser")
    public String updateUser() {

        log.info("修改用户信息");

        return "修改成功... 😝";
    }



}
