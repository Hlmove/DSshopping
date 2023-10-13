package cn.Hlmove.controllers;

import cn.Hlmove.entities.TProductProductEntity;
import cn.Hlmove.service.TProductProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Hlmove
 * 2023/09/20 11:16 回顾时间
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    TProductProductService productService;

    //商品列表
    @RequestMapping("/index")
    public String index(Integer pageIndex, Model model){

        if(pageIndex==null) {
            pageIndex=1;
        }
        int pageSize = 8;

        //分页数据集
        List<TProductProductEntity> products = productService.list(pageIndex, pageSize);
        model.addAttribute("products", products);

        //分码组件数据
        int firstpage=1;
        //前一页页码
        int prepage = pageIndex-1;
        if(pageIndex==1) {
            prepage = 1;
        }
        //总页数（数据表记录数16，pageSize=5，请计算一共有几页）
        int recordCount = productService.selectRecordCount();
        int totalpagenum = recordCount/pageSize;
        if((recordCount%pageSize)!=0) {
            totalpagenum+=1;
        }
        if(totalpagenum==0) {
            totalpagenum=1;
        }
        //下一页页码
        int nextpage=totalpagenum;
        if(pageIndex < totalpagenum) {
            nextpage=pageIndex+1;
        }
        int lastpage = totalpagenum;
        model.addAttribute("firstpage", firstpage);
        model.addAttribute("prepage", prepage);
        model.addAttribute("totalpagenum", totalpagenum);
        model.addAttribute("nextpage", nextpage);
        model.addAttribute("lastpage", lastpage);
        //分码组件数据
        return "/product/index";
    }

    //商品详情
    @RequestMapping("/detail")
    public String detail(int productid, Model model){
        TProductProductEntity product =  productService.findById(productid);
        model.addAttribute("product", product);
        return "product/detail";
    }


}
