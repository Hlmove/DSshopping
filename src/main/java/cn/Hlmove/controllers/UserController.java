package cn.Hlmove.controllers;

import cn.Hlmove.entities.TUserUserEntity;
import cn.Hlmove.service.TUserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    TUserUserService userService;

    //界面呈现
    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

    //采集数据
    @PostMapping("/register")
    public String register(TUserUserEntity user){

        user.setUsergroupid(1);
        user.setRegtime(new Timestamp(new Date().getTime()));
        user.setModifytime(new Timestamp(new Date().getTime()));
        int result = userService.insert(user);


        return "redirect:../index";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String username, String userpwd, HttpSession session, HttpServletRequest request){
        TUserUserEntity user = userService.login(username, userpwd);
        if(user==null){
            return "user/login";
        }
        session.setAttribute("username", username);
        session.setAttribute("userid", user.getUserid());
        session.setAttribute("loginip", request.getRemoteAddr());
        session.setAttribute("logintime", new Date());
        return "redirect:/index";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清除所有Session变量
        session.invalidate();
        return "redirect:index";
    }

}
