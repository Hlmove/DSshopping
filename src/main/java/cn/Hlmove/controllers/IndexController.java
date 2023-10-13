package cn.Hlmove.controllers;

import cn.Hlmove.entities.TOrderCartEntity;
import cn.Hlmove.service.TOrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    TOrderCartService cartService;

    //首页  /index
    @RequestMapping("/index")
    public String index(Model model, HttpSession session){

        //计算总金额
        double sum = 0.00;
        int count = 0;
        if(session.getAttribute("username") != null){
            //购物车信息
            //加载购物车所有信息，显示购物车信息
            int userid = Integer.valueOf(session.getAttribute("userid").toString());
            List<TOrderCartEntity> cartEntities = cartService.list(userid);
            for(TOrderCartEntity cartEntity : cartEntities){
                sum+= cartEntity.getProductPrice() * cartEntity.getProductNum();
                count++;
            }
        }
        model.addAttribute("orderSum", sum);
        model.addAttribute("orderCount", count);


        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        //默认的返回值，是一个视图名
        //加载视图
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        //国外退出是有界面，国内很少有界面
        //清理性工作
        //直接跳转即可
        return "redirect:index";
    }


}
