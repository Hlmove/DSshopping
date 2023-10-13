package cn.Hlmove.service;

import cn.Hlmove.dao.TOrderCartDao;
import cn.Hlmove.entities.TOrderCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层
//@Component                      //纳入Spring管理（ new TAdminAdminService()==>Spring容器 ）
@Service                        //语义化注解
public class TOrderCartService {

    @Autowired
    TOrderCartDao dao;

    public List<TOrderCartEntity> list(){
        List<TOrderCartEntity> entities = dao.select();
        return entities;
    }

    public List<TOrderCartEntity> list(int userid){
        List<TOrderCartEntity> entities = dao.selectByUserid(userid);
        return entities;
    }

    //分页支持
    public List<TOrderCartEntity> list(int pageIndex, int pageSize){
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TOrderCartEntity> entities = dao.selectPager(offset, length);
        return entities;
    }

    //分页支持-返回总记录数
    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TOrderCartEntity entity){
        //dao insert
        return dao.insert(entity);
    }

    public TOrderCartEntity findById(int id){
        //dao insert
        TOrderCartEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TOrderCartEntity entity){
        //调用Dao层代码
        int result = dao.update(entity);
        return result;
    }

    public int remove(int id){
        //调用Dao层代码
        int result = dao.delete(id);
        return result;
    }

    //清空该用户的购物车
    public int removeByUserid(int userid){
        //调用Dao层代码
        int result = dao.deleteByUserid(userid);
        return result;
    }
}
