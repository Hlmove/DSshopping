package cn.Hlmove.SysController;

import cn.Hlmove.entities.TAdminAdminEntity;
import cn.Hlmove.entities.TAdminGroupEntity;
import cn.Hlmove.service.TAdminAdminService;
import cn.Hlmove.service.TAdminGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 后台管理员相关
 */
@Controller
@RequestMapping("/sys/admin")
public class SysAdminController {

    @Autowired
    TAdminAdminService adminService;

    @Autowired
    TAdminGroupService groupService;

    //修改密码--呈现视图
    @GetMapping("/mypassword")
    public String mypassword(HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        return "sys/admin/mypassword";
    }

    //修改密码--采集新密码，进行修改
    @PostMapping("/mypassword")
    public String mypassword(String userpwd, String reuserpwd, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        //调用Service ==> Dao
        if (!userpwd.equals(reuserpwd)) {
            //提示两次密码输入不一致
            return "sys/admin/mypassword";
        }

        String adminName = session.getAttribute("adminName").toString();
        int result = adminService.modifyPwd(adminName, userpwd);

        return "redirect:/sys/frame";
    }

    //我的资料
    @RequestMapping("/profile")
    public String profile(Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }
        List<TAdminAdminEntity> entities = adminService.list();
        model.addAttribute("entities", entities);
        List<TAdminGroupEntity> groups = groupService.list2();
        model.addAttribute("groups", groups);
        return "sys/admin/profile";
    }


    //管理员列表
    @RequestMapping("/list")
    public String list(Integer pageIndex, Integer pageSize, Model model, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        if(pageIndex==null)pageIndex=1;
        if(pageSize==null)pageSize=5;
        List<TAdminAdminEntity> entities = adminService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = adminService.selectRecordCount();
        int totalpagenum = recordCount/pageSize;
        if((recordCount%pageSize)!=0)totalpagenum+=1;
        //下一页页码
        int nextpage=totalpagenum;
        if(pageIndex < totalpagenum)
            nextpage=pageIndex+1;
        model.addAttribute("prepage", prepage);
        model.addAttribute("totalpagenum", totalpagenum);
        model.addAttribute("nextpage", nextpage);
        //分页连接组件 数据支持

        //加载视图，携带列表数据
        return "sys/admin/list";

    }

    //管理员 添加（呈现界面）
    @GetMapping("/add")
    public ModelAndView add(ModelAndView mav, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            //return "redirect:login";
            mav.setViewName("redirect:/sys/login");
        }


        //加载 管理员分组表数据集
        List<TAdminGroupEntity> groups = groupService.list2();

        //ModelAndView（即能传递数据，又能处理视图）
        //Controller >> View 传递数据
        mav.addObject("groups", groups);
        mav.setViewName("sys/admin/add");
        //我的资料获取
        return mav;

        //加载视图，这个视图，需要提供一个管理员分组表数据集，初始化所属组
        //return "sys/admin/add";
    }

    //数据采集
    /*@PostMapping("/add")
    public String add(Integer admingroupid, String adminname, String adminpwd, String gender, String description){

        //提交服务层，调用dao层，完成数据持久化
        TAdminAdminEntity admin = new TAdminAdminEntity();
        admin.setAdmingroupid(admingroupid);
        admin.setAdminname(adminname);
        admin.setAdminpwd(adminpwd);
        admin.setGender(gender);
        admin.setDescription(description);

        adminService.insert(admin);

        return "redirect:list";
    }*/

    //数据采集
    @PostMapping("/add")
    public String add(TAdminAdminEntity admin, HttpSession session){

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }


        //提交服务层，调用dao层，完成数据持久化
        adminService.insert(admin);
        //return "sys/admin/list";
        return "redirect:list";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify")
    public String modify(int adminId, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        //加载 管理员分组表数据集
        List<TAdminGroupEntity> groups = groupService.list2();
        //把原来的管理员数据下载回来
        TAdminAdminEntity adminEntity = adminService.findById(adminId);
        //分组数据
        model.addAttribute("groups", groups);
        //当前管理员数据
        model.addAttribute("adminEntity", adminEntity);
        return "sys/admin/modify";
    }

    @PostMapping("/modify")
    public String modify(TAdminAdminEntity entity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        Timestamp now = new Timestamp(new Date().getTime());
        entity.setModifytime(now);
        int result = adminService.modify(entity);
        return "redirect:list";
    }

    @RequestMapping("/remove")
    public String remove(int adminid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        //调用三层，完成删除
        int result = adminService.remove(adminid);

        return "redirect:list";
    }


    /**********************************************************************************************************************************************************************************************************************************
     * ****************************************************************************************************************************************************************************************************************
     ****************************************************************************************************************************************************************************************************************
     分割线
     */



    //管理员分组
    @RequestMapping("/list2")
    public String list2(Integer pageIndex, Integer pageSize, Model model, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        if(pageIndex==null)pageIndex=1;
        if(pageSize==null)pageSize=5;
        List<TAdminGroupEntity> entitie = groupService.list2(pageIndex, pageSize);
        model.addAttribute("entitie", entitie);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = groupService.selectRecordCount();
        int totalpagenum = recordCount/pageSize;
        if((recordCount%pageSize)!=0)totalpagenum+=1;
        //下一页页码
        int nextpage=totalpagenum;
        if(pageIndex < totalpagenum)
            nextpage=pageIndex+1;
        model.addAttribute("prepage", prepage);
        model.addAttribute("totalpagenum", totalpagenum);
        model.addAttribute("nextpage", nextpage);
        //分页连接组件 数据支持

        //加载视图，携带列表数据
        return "sys/admin/list2";
    }




    //管理员 添加（呈现界面）
    @GetMapping("/add2")
    public ModelAndView add2(ModelAndView mav, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            //return "redirect:login";
            mav.setViewName("redirect:/sys/login");
        }


        //加载 管理员分组表数据集
        List<TAdminGroupEntity> groups = groupService.list2();

        //ModelAndView（即能传递数据，又能处理视图）
        //Controller >> View 传递数据
        mav.addObject("groups", groups);
        mav.setViewName("sys/admin/add2");
        return mav;

        //加载视图，这个视图，需要提供一个管理员分组表数据集，初始化所属组
        //return "sys/admin/add";
    }


    //数据采集
    @PostMapping("/add2")
    public String add2(TAdminGroupEntity group, HttpSession session){

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }


        //提交服务层，调用dao层，完成数据持久化
        groupService.insert(group);
        //return "sys/admin/list";
        return "redirect:list2";
    }

    @RequestMapping("/remove2")
    public String remove2(int admingroupid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        //调用三层，完成删除
        int result = groupService.remove(admingroupid);

        return "redirect:list2";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify2")
    public String modify2(int admingroupid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        //加载 管理员分组表数据集
        List<TAdminGroupEntity> groups = groupService.list2();
        //把原来的管理员数据下载回来
        TAdminGroupEntity groupEntity = groupService.findById(admingroupid);
        //分组数据
        model.addAttribute("groups", groups);
        //当前管理员数据
        model.addAttribute("groupEntity", groupEntity);
        return "sys/admin/modify2";
    }

    @PostMapping("/modify2")
    public String modify2(TAdminGroupEntity entity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:/sys/login";
        }

        int result = groupService.modify(entity);
        return "redirect:list2";
    }





}
