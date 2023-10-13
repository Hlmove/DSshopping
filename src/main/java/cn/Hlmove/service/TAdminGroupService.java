package cn.Hlmove.service;

import cn.Hlmove.dao.TAdminGroupDao;

import cn.Hlmove.entities.TAdminGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TAdminGroupService {

    @Autowired
    TAdminGroupDao dao;

    public List<TAdminGroupEntity> list2(){
        //shift alt l
        List<TAdminGroupEntity> groups = dao.select();
        return groups;
    }



    public List<TAdminGroupEntity> list2(Integer pageIndex, Integer pageSize) {
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TAdminGroupEntity> entitie = dao.selectPager(offset, length);
        return entitie;
    }


    //分页支持-返回总记录数
    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TAdminGroupEntity entity){
        //dao insert
        return dao.insert(entity);
    }

    public int remove(int admingroupid){
        //调用Dao层代码
        int result = dao.delete(admingroupid);
        return result;
    }



    public TAdminGroupEntity findById(int id){
        //dao insert
        TAdminGroupEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TAdminGroupEntity entity){
        //调用Dao层代码
        int result = dao.update(entity);
        return result;
    }
}
