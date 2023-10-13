package cn.Hlmove.service;

import cn.Hlmove.dao.TAdminAdminDao;
import cn.Hlmove.dao.TProductProductDao;
import cn.Hlmove.entities.TProductProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层
//@Component                      //纳入Spring管理（ new TAdminAdminService()==>Spring容器 ）
@Service                        //语义化注解
public class TProductProductService {

    @Autowired
    TProductProductDao dao;

    //管理员列表
    public List<TProductProductEntity> list(){
        //调用Dao层代码
        List<TProductProductEntity> entities = dao.select();
        return entities;
    }

    //分页支持
    public List<TProductProductEntity> list(int pageIndex, int pageSize){
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TProductProductEntity> entities = dao.selectPager(offset, length);
        return entities;
    }

    //分页支持-返回总记录数
    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TProductProductEntity entity){
        //dao insert
        return dao.insert(entity);
    }

    public TProductProductEntity findById(int id){
        //dao insert
        TProductProductEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TProductProductEntity entity){
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
