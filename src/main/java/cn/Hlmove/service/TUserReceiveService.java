package cn.Hlmove.service;

import cn.Hlmove.dao.TUserReceiveDao;
import cn.Hlmove.entities.TUserReceiveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层
//@Component                      //纳入Spring管理（ new TAdminAdminService()==>Spring容器 ）
@Service                        //语义化注解
public class TUserReceiveService {

    @Autowired
    TUserReceiveDao dao;

    public List<TUserReceiveEntity> list(){
        List<TUserReceiveEntity> entities = dao.select();
        return entities;
    }

    //分页支持
    public List<TUserReceiveEntity> list(int pageIndex, int pageSize){
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TUserReceiveEntity> entities = dao.selectPager(offset, length);
        return entities;
    }

    //分页支持-返回总记录数
    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TUserReceiveEntity entity){
        //dao insert
        return dao.insert(entity);
    }

    public TUserReceiveEntity findById(int id){
        //dao insert
        TUserReceiveEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TUserReceiveEntity entity){
        //调用Dao层代码
        int result = dao.update(entity);
        return result;
    }

    public int remove(int id){
        //调用Dao层代码
        int result = dao.delete(id);
        return result;
    }

    //以上未确认，没修改完

    //获取默认收货人信息
    public TUserReceiveEntity selectDefaultByUserId(int userid){
        return dao.selectDefaultByUserId(userid);
    }
}
