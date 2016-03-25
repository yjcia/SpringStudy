package com.spring.controller;

import com.spring.model.User;
import com.spring.service.IUserService;
import com.spring.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanJun on 2016/2/1.
 */
@Controller
public class UserOperationController {

    private static final Logger logger = LoggerFactory.getLogger(UserOperationController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String userLogin(){
        logger.debug("user login!");
        return "login";
    }

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(){
        logger.debug("user home!");
        return "home";
    }


    @RequestMapping(value = "/checkLoginEmail")
    @ResponseBody
    public Map<String,Object> checkLoginEmail(@RequestParam("email") String email) {
        if(!StringUtil.isEmpty(email)){
            User u = userService.findUserByEmail(email);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("valid",u != null ? "true" : "false");
            return map;
        }
        return null;
    }

    @RequestMapping(value = "/doLogin" , method = RequestMethod.POST)
    public String checkLoginEmailAndPwd(@RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        Model model) {
        String errMsg = "";
        if(!StringUtil.isEmpty(email) && !StringUtil.isEmpty(password)){
            User u = userService.findUserByEmailAndPwd(email,password);
            if(u != null){
                return "redirect:home";
            }else{
                errMsg = "User Account Error ";
            }
        }
        model.addAttribute("errMsg",errMsg);
        return "login";
    }


}
