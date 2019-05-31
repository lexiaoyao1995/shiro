package com.example.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("login")
    public String login(String username,String password){

        //1、获取subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "success";
        }catch (UnknownAccountException e){
            return "用户名不存在";
        }catch (IncorrectCredentialsException e){
            return "密码错误";
        }
    }
    @GetMapping("list")
    public String list(){
        return "list";
    }

    @GetMapping("add")
    public String add(){
        return "add";
    }

    @GetMapping("noAuth")
    public String noAuth(){
        return "noAuth";
    }


}
