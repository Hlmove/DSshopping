package cn.Hlmove.SysController;

import cn.Hlmove.entities.TAdminAdminEntity;
import cn.Hlmove.service.TAdminAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/sys")
public class SysIndexController {

    @Autowired
    TAdminAdminService adminService;

    //呈现视图
    @GetMapping("/login")           //http  get
    public String login(){
        return "sys/login";
    }

    //采集数据
    @PostMapping("login")           //http post
    public String login(String username, String userpwd, HttpSession session, HttpServletRequest request){

        //获取用户名、密码后，应调用Model，完成数据库检验
        //TAdminAdminService service = new TAdminAdminService();
        TAdminAdminEntity adminUser = adminService.login(username, userpwd);
        if(adminUser != null){
            //用户名、密码验证正确
            //使用 Session 记录 ，当前用户身份信息
            session.setAttribute("adminName", adminUser.getAdminname());
            session.setAttribute("adminId", adminUser.getAdminid());
            session.setAttribute("ip", request.getRemoteAddr());
            session.setAttribute("loginTime", new Date());

            return "redirect:frame";
        }else{
            //验证失败
            return "sys/login";
        }
    }

    @RequestMapping("/frame")
    public String frame(HttpSession session){

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:login";
        }


        return "sys/frame";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        //清理登录时，记录的数据
        session.removeAttribute("adminName");
        session.removeAttribute("adminId");
        session.removeAttribute("ip");
        session.removeAttribute("loginTime");

        return "redirect:login";
    }

}
