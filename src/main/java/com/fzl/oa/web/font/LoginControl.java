package com.fzl.oa.web.font;

import com.fzl.oa.web.entity.Return;
import com.fzl.oa.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xixin on 2017/10/11.
 */

@Controller
public class LoginControl {
    private  static  final Logger logger = LoggerFactory.getLogger(LoginControl. class);

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public @ResponseBody
    Return login(String name, String psd){
        Return r=userService.findUser(name,psd);
        return  r;
    }

}
