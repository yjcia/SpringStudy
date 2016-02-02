package com.spring.controller;

import com.spring.model.User;
import com.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanJun on 2016/2/1.
 */
@Controller
public class UserOperationController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String userLogin(){
        System.out.println("user login!");
        return "login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(String email,String password){
        return "success";
    }

    @RequestMapping(value = "/checkLoginEmail")
    @ResponseBody
    public Map<String,Object> checkLoginEmail(@RequestParam("email") String email) {
        User u = userService.findUserByEmail(email);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("valid",u != null ? "true" : "false");
        return map;
    }

}
