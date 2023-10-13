package cn.Hlmove.controllers;

import cn.Hlmove.entities.*;
import cn.Hlmove.service.TOrderCartService;
import cn.Hlmove.service.TOrderOrderService;
import cn.Hlmove.service.TProductProductService;
import cn.Hlmove.service.TUserReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    TProductProductService productService;

    @Autowired
    TOrderCartService cartService;

    @Autowired
    TUserReceiveService receiveService;

    @Autowired
    TOrderOrderService orderService;

    //购物车
    @RequestMapping("/gwc1")
    public String gwc1(Integer productid, Integer buynum, HttpSession session, Model model){

        if(session.getAttribute("username")==null){
            return "redirect:/user/login";
        }

        int userid = Integer.valueOf(session.getAttribute("userid").toString());

        if(productid!=null || buynum!=null) {
            //根据productid,获取将要购买的商品信息
            TProductProductEntity product = productService.findById(productid);

            //将商品信息、购买数量、购买者的用户编号  插入购物车信息表
            TOrderCartEntity cartEntity = new TOrderCartEntity();
            cartEntity.setUserid(userid);
            cartEntity.setProductid(productid);
            cartEntity.setProductName(product.getProductname());
            cartEntity.setProductPrice(product.getPrice());
            cartEntity.setProductNum(buynum);
            cartEntity.setBuytime(new Timestamp(new Date().getTime()));
            int result = cartService.insert(cartEntity);
        }

        //加载购物车所有信息，显示购物车信息
        List<TOrderCartEntity> cartEntities = cartService.list(userid);
        model.addAttribute("cartEntities", cartEntities);

        //计算总金额
        double sum = 0.0;
        for(TOrderCartEntity cartEntity : cartEntities){
            sum += cartEntity.getProductPrice() * cartEntity.getProductNum();
        }
        model.addAttribute("orderSum", sum);

        return "order/gwc1";
    }

    //确认购物车、确认收货人、确认支付方式...
    @RequestMapping("/gwc2")
    public String gwc2(Model model, HttpSession session){

        if(session.getAttribute("username")==null){
            return "redirect:/user/login";
        }

        int userid = Integer.valueOf(session.getAttribute("userid").toString());

        //加载购物车所有信息，显示购物车信息
        List<TOrderCartEntity> cartEntities = cartService.list(userid);
        model.addAttribute("cartEntities", cartEntities);

        //计算总金额
        double sum = 0.0;
        for(TOrderCartEntity cartEntity : cartEntities){
            sum += cartEntity.getProductPrice() * cartEntity.getProductNum();
        }
        model.addAttribute("orderSum", sum);

        //获取默认收货信息
        TUserReceiveEntity receiveEntity = receiveService.selectDefaultByUserId(userid);
        model.addAttribute("receiveEntity", receiveEntity);

        return "order/gwc2";
    }

    //生成订单
    @RequestMapping("/gwc3")
    public String gwc3(TUserReceiveEntity receiver, String payMehtod, HttpSession session, Model model){
        if(session.getAttribute("username")==null){
            return "redirect:/user/login";
        }

        int userid = Integer.valueOf(session.getAttribute("userid").toString());

        //加载购物车所有信息，显示购物车信息
        List<TOrderCartEntity> cartEntities = cartService.list(userid);//select

        //计算总金额
        double sum = 0.0;
        for(TOrderCartEntity cartEntity : cartEntities){
            sum += cartEntity.getProductPrice() * cartEntity.getProductNum();
        }

        //订单编号
        int orderId = UUID.randomUUID().toString().replaceAll("-", "").hashCode();
       // int orderId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

        //生成订单
        orderService.generateOrder(userid, receiver, orderId, sum);


        model.addAttribute("orderSum", sum);
        model.addAttribute("orderId", orderId);
        return "order/gwc3";
    }


}
