package cn.Hlmove.SysController;

import cn.Hlmove.entities.TProductBrandEntity;
import cn.Hlmove.entities.TProductClassEntity;
import cn.Hlmove.entities.TProductProductEntity;
import cn.Hlmove.entities.TAdminGroupEntity;
import cn.Hlmove.service.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 后台管理员相关
 */
@Controller
@RequestMapping("/sys/product")
public class SysProductController {

    @Autowired
    TProductProductService productService;

    @Autowired
    TProductClassService classService;

    @Autowired
    TProductBrandService brandService;

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
        List<TProductProductEntity> entities = productService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = productService.selectRecordCount();
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
        return "sys/product/list";
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

        List<TProductClassEntity> classEntities = classService.list();
        List<TProductBrandEntity> brandEntities = brandService.list();

        mav.addObject("classEntities", classEntities);
        mav.addObject("brandEntities", brandEntities);
        mav.setViewName("sys/product/add");
        return mav;
    }

    //数据采集
    @PostMapping("/add")
    public String add(TProductProductEntity entity, MultipartFile smallimgFile, MultipartFile bigimgFile, HttpSession session) throws IOException {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //缩略图保存
        if(!smallimgFile.isEmpty()){
            String oldFilename = smallimgFile.getOriginalFilename();
            String fileSuffix = oldFilename.substring(oldFilename.lastIndexOf("."), oldFilename.length());
            String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;
            smallimgFile.transferTo(new File("G:\\imageserver\\"+newFilename));
            entity.setSmallimg(newFilename);
        }

        //原图保存
        if(!bigimgFile.isEmpty()){
            String oldFilename = bigimgFile.getOriginalFilename();
            String fileSuffix = oldFilename.substring(oldFilename.lastIndexOf("."), oldFilename.length());
            String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;
            bigimgFile.transferTo(new File("G:\\imageserver\\"+newFilename));
            entity.setBigimg(newFilename);
        }


        //提交服务层，调用dao层，完成数据持久化
        productService.insert(entity);
        //return "sys/admin/list";
        return "redirect:list";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify")
    public String modify(int productid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TProductProductEntity entity = productService.findById(productid);

        List<TProductClassEntity> classEntities = classService.list();
        List<TProductBrandEntity> brandEntities = brandService.list();

        model.addAttribute("classEntities", classEntities);
        model.addAttribute("brandEntities", brandEntities);
        //当前管理员数据
        model.addAttribute("entity", entity);
        return "sys/product/modify";
    }

    @PostMapping("/modify")
    public String modify(TProductProductEntity entity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = productService.modify(entity);
        return "redirect:list";
    }

    @RequestMapping("/remove")
    public String remove(int productid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result = productService.remove(productid);

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
        List<TProductClassEntity> entities = classService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = classService.selectRecordCount();
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
        return "sys/product/list2";
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

        List<TProductClassEntity> classEntities = classService.list();


        mav.addObject("classEntities", classEntities);

        mav.setViewName("sys/product/add2");
        return mav;
    }

    //数据采集
    @PostMapping("/add2")
    public String add2(TProductClassEntity entity, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }


        //提交服务层，调用dao层，完成数据持久化
        classService.insert(entity);
        //return "sys/admin/list";
        return "redirect:/sys/product/list2";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify2")
    public String modify2(int classid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TProductClassEntity classEntity = classService.findById(classid);


        //当前管理员数据
        model.addAttribute("classEntity", classEntity);
        return "sys/product/modify2";
    }

    @PostMapping("/modify2")
    public String modify2(TProductClassEntity classEntity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = classService.modify(classEntity);
        //
        return "redirect:/sys/product/list2";
    }

    @RequestMapping("/remove2")
    public String remove2(int classid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result = classService.remove(classid);

        return "redirect:/sys/product/list2";
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
        List<TProductBrandEntity> entities = brandService.list(pageIndex, pageSize);
        model.addAttribute("entities", entities);

        //分页连接组件 数据支持
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1)
            prepage = 1;
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = brandService.selectRecordCount();
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
        return "sys/product/list3";
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

        List<TProductBrandEntity> brandEntities = brandService.list();


        mav.addObject("brandEntities", brandEntities);

        mav.setViewName("sys/product/add3");
        return mav;
    }

    //数据采集
    @PostMapping("/add3")
    public String add3(TProductBrandEntity entity, HttpSession session) {

        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }


        //提交服务层，调用dao层，完成数据持久化
        brandService.insert(entity);
        //return "sys/admin/list";
        return "redirect:/sys/product/list3";
    }

    //管理员 添加（呈现界面）
    @GetMapping("/modify3")
    public String modify3(int brandid, Model model, HttpSession session) {
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //把原来的管理员数据下载回来
        TProductBrandEntity brandEntity = brandService.findById(brandid);


        //当前管理员数据
        model.addAttribute("brandEntity", brandEntity);
        return "sys/product/modify3";
    }

    @PostMapping("/modify3")
    public String modify3(TProductBrandEntity brandEntity, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        int result = brandService.modify(brandEntity);
        //
        return "redirect:/sys/product/list3";
    }

    @RequestMapping("/remove3")
    public String remove3(int brandid, HttpSession session){
        //权限控制
        if(session.getAttribute("adminName")==null){
            //非正常访问者或是超时访问者，都应该重新登录
            return "redirect:../login";
        }

        //调用三层，完成删除
        int result = brandService.remove(brandid);

        return "redirect:/sys/product/list3";
    }

























}
