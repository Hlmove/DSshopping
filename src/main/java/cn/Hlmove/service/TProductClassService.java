package cn.Hlmove.service;

import cn.Hlmove.dao.TProductClassDao;
import cn.Hlmove.dao.TProductProductDao;
import cn.Hlmove.entities.TProductClassEntity;
import cn.Hlmove.entities.TProductProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层
//@Component                      //纳入Spring管理（ new TAdminAdminService()==>Spring容器 ）
@Service                        //语义化注解
public class TProductClassService {

    @Autowired
    TProductClassDao dao;

    public List<TProductClassEntity> list(){
        List<TProductClassEntity> entities = dao.select();
        return entities;
    }

    //分页支持
    public List<TProductClassEntity> list(int pageIndex, int pageSize){
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TProductClassEntity> entities = dao.selectPager(offset, length);
        return entities;
    }

    //分页支持-返回总记录数
    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TProductClassEntity entity){
        //dao insert
        return dao.insert(entity);
    }

    public TProductClassEntity findById(int id){
        //dao insert
        TProductClassEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TProductClassEntity entity){
        //调用Dao层代码
        int result = dao.update(entity);
        return result;
    }

    public int remove(int id){
        //调用Dao层代码
        int result = dao.delete(id);
        return result;
    }
}
