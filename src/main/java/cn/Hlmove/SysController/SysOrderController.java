package cn.Hlmove.SysController;

import cn.Hlmove.entities.*;
import cn.Hlmove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 后台管理员相关
 */
@Controller
@RequestMapping("/sys/order")
public class SysOrderController {

    @Autowired
    TOrderCartService cartService;

    @Autowired
    TOrderOrderService orderService;

    @Autowired
    TUserUserService  userService;

    @Autowired
    TProductProductService productService;

   // @Autowired
    //TProductBrandService brandService;

    //管理员列表
    @RequestMapping("/list")
    public String list(Integer pageIndex, Integer pageSize, Model model, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        if(pageIndex==null)pageIndex=1;
        if(pageSize==null)pageSize=5;
        List<TOrderOrderEntity> entities = orderService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = orderService.selectRecordCount();
        int totalpagenum = recordCount/pageSize;
        if((recordCount%pageSize)!=0)totalpagenum+=1;
        if(totalpagenum==0)totalpagenum=1;
        //下一页页码
        int nextpage=totalpagenum;
        if(pageIndex < totalpagenum)
            nextpage=pageIndex+1;
        model.addAttribute("prepage", prepage);
        model.addAttribute("totalpagenum", totalpagenum);
        model.addAttribute("nextpage", nextpage);
        //分页连接组件 数据支持

        //加载视图，携带列表数据
        return "sys/order/list";
    }


    //管理员 添加（呈现界面）
    @GetMapping("/modify")
    public String modify(String orderid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TOrderOrderEntity entity = orderService.findById(orderid);


       // model.addAttribute("classEntities", classEntities);
       // model.addAttribute("brandEntities", brandEntities);
        //当前管理员数据
        model.addAttribute("entity", entity);
        return "sys/order/modify";
    }

    @PostMapping("/modify")
    public String modify(TOrderOrderEntity entity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = orderService.modify(entity);
        return "redirect:list";
    }

    @RequestMapping("/remove")
    public String remove(String orderid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
       int  result = orderService.remove(orderid);

        return "redirect:list";
    }




    /**********************************************************************************************************************************************************************************************************************************
     * ****************************************************************************************************************************************************************************************************************
     ****************************************************************************************************************************************************************************************************************
     分割线
     */



    //管理员列表
    @RequestMapping("/list2")
    public String list2(Integer pageIndex, Integer pageSize, Model model, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        if(pageIndex==null)pageIndex=1;
        if(pageSize==null)pageSize=5;
        List<TOrderCartEntity> entities = cartService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = cartService.selectRecordCount();
        int totalpagenum = recordCount/pageSize;
        if((recordCount%pageSize)!=0)totalpagenum+=1;
        if(totalpagenum==0)totalpagenum=1;
        //下一页页码
        int nextpage=totalpagenum;
        if(pageIndex < totalpagenum)
            nextpage=pageIndex+1;
        model.addAttribute("prepage", prepage);
        model.addAttribute("totalpagenum", totalpagenum);
        model.addAttribute("nextpage", nextpage);
        //分页连接组件 数据支持

        //加载视图，携带列表数据
        return "sys/order/list2";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/add2")
    public ModelAndView add2(ModelAndView mav, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            //return "redirect:login";
            mav.setViewName("redirect:../login");
        }

        List<TOrderCartEntity> cartEntities = cartService.list();
       List<TUserUserEntity> userEntities = userService.list();
        List<TProductProductEntity> productEntities = productService.list();

        mav.addObject("cartEntities", cartEntities);
        mav.addObject("userEntities", userEntities);
       mav.addObject("productEntities", productEntities);

        mav.setViewName("sys/order/add2");
        return mav;
    }

    //数据采集
    @PostMapping("/add2")
    public String add2(TOrderCartEntity entity, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }


        //提交服务层，调用dao层，完成数据持久化
        cartService.insert(entity);
        //return "sys/admin/list";
        return "redirect:/sys/order/list2";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify2")
    public String modify2(int cartid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TOrderCartEntity cartEntity = cartService.findById(cartid);


        //当前管理员数据
        model.addAttribute("cartEntity", cartEntity);
        return "sys/order/modify2";
    }

    @PostMapping("/modify2")
    public String modify2(TOrderCartEntity classEntity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = cartService.modify(classEntity);
        //
        return "redirect:/sys/order/list2";
    }

    @RequestMapping("/remove2")
    public String remove2(int cartid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result = cartService.remove(cartid);

        return "redirect:/sys/order/list2";
    }
























}
