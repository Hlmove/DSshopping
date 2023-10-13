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
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    TUserUserService userService;

    @Autowired
    TUserGroupService groupService;

    @Autowired
    TUserReceiveService receiveService;

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
        List<TUserUserEntity> userEntities = userService.list(pageIndex, pageSize);

        model.addAttribute("userEntities", userEntities);


        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = userService.selectRecordCount();
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
        return "sys/user/list";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/add")
    public ModelAndView add(ModelAndView mav, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            //return "redirect:login";
            mav.setViewName("redirect:../login");
        }

        List<TUserGroupEntity> groupEntities =groupService.list2();
    //    List<TProductBrandEntity> brandEntities = brandService.list();

        mav.addObject("groupEntities", groupEntities);
      //  mav.addObject("brandEntities", brandEntities);
        mav.setViewName("sys/user/add");
        return mav;
    }

    //数据采集
    @PostMapping("/add")
    public String add(TUserUserEntity entity,  HttpSession session)  {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }




        //提交服务层，调用dao层，完成数据持久化
       userService.insert(entity);
        //return "sys/admin/list";
        return "redirect:list";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify")
    public String modify(int userid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TUserUserEntity userEntity = userService.findById(userid);

        List<TUserGroupEntity> groupEntities =groupService.list2();
     //   List<TProductBrandEntity> brandEntities = brandService.list();

      //  model.addAttribute("classEntities", classEntities);
        model.addAttribute("groupEntities", groupEntities);
        //当前管理员数据
        model.addAttribute("userEntity", userEntity);
        return "sys/user/modify";
    }

    @PostMapping("/modify")
    public String modify(TUserUserEntity entity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = userService.modify(entity);
        return "redirect:list";
    }

    @RequestMapping("/remove")
    public String remove(int userid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result = userService.remove(userid);

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
        List<TUserGroupEntity> entities = groupService.list2(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount =groupService.selectRecordCount();
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
        return "sys/user/list2";
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

        List<TUserGroupEntity> groupEntities = groupService.list2();


        mav.addObject("groupEntities", groupEntities);

        mav.setViewName("sys/user/add2");
        return mav;
    }

    //数据采集
    @PostMapping("/add2")
    public String add2(TUserGroupEntity entity, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }


        //提交服务层，调用dao层，完成数据持久化
        groupService.insert(entity);
        //return "sys/admin/list";
        return "redirect:/sys/user/list2";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify2")
    public String modify2(int usergroupid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TUserGroupEntity groupEntity = groupService.findById(usergroupid);


        //当前管理员数据
        model.addAttribute("groupEntity", groupEntity);
        return "sys/user/modify2";
    }

    @PostMapping("/modify2")
    public String modify2(TUserGroupEntity classEntity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = groupService.modify(classEntity);
        //
        return "redirect:/sys/user/list2";
    }

    @RequestMapping("/remove2")
    public String remove2(int usergroupid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result =groupService.remove(usergroupid);

        return "redirect:/sys/user/list2";
    }






    /**********************************************************************************************************************************************************************************************************************************
     * ****************************************************************************************************************************************************************************************************************
     ****************************************************************************************************************************************************************************************************************
     分割线
     */



    //管理员列表
    @RequestMapping("/list3")
    public String list3(Integer pageIndex, Integer pageSize, Model model, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        if(pageIndex==null)pageIndex=1;
        if(pageSize==null)pageSize=5;
        List<TUserReceiveEntity> entities = receiveService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = receiveService.selectRecordCount();
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
        return "sys/user/list3";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/add3")
    public ModelAndView add3(ModelAndView mav, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            //return "redirect:login";
            mav.setViewName("redirect:../login");
        }

        List<TUserUserEntity> brandEntities = userService.list();


        mav.addObject("brandEntities", brandEntities);

        mav.setViewName("sys/user/add3");
        return mav;
    }

    //数据采集
    @PostMapping("/add3")
    public String add3(TUserReceiveEntity entity, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }


        //提交服务层，调用dao层，完成数据持久化
        receiveService.insert(entity);
        //return "sys/admin/list";
        return "redirect:/sys/user/list3";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify3")
    public String modify3(int receiveid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TUserReceiveEntity brandEntity = receiveService.findById(receiveid);


        //当前管理员数据
        model.addAttribute("brandEntity", brandEntity);
        return "sys/user/modify3";
    }

    @PostMapping("/modify3")
    public String modify3(TUserReceiveEntity brandEntity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = receiveService.modify(brandEntity);
        //
        return "redirect:/sys/user/list3";
    }

    @RequestMapping("/remove3")
    public String remove3(int receiveid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result = receiveService.remove(receiveid);

        return "redirect:/sys/user/list3";
    }

























}
