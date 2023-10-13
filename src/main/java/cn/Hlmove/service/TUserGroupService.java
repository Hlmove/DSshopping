package cn.Hlmove.service;

import cn.Hlmove.dao.TUserGroupDao;
import cn.Hlmove.entities.TUserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TUserGroupService {
    @Autowired
    TUserGroupDao dao;

    public List<TUserGroupEntity> list2() {
        //shift alt l
        List<TUserGroupEntity> groups = dao.select();
        return groups;
    }






    public List<TUserGroupEntity> list2(Integer pageIndex, Integer pageSize) {
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TUserGroupEntity> entitie = dao.selectPager(offset, length);
        return entitie;
    }


    //分页支持-返回总记录数
    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TUserGroupEntity entity){
        //dao insert
        return dao.insert(entity);
    }

    public int remove(int usergroupid){
        //调用Dao层代码
        int result = dao.delete(usergroupid);
        return result;
    }



    public TUserGroupEntity findById(int id){
        //dao insert
        TUserGroupEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TUserGroupEntity entity){
        //调用Dao层代码
        int result = dao.update(entity);
        return result;
    }
}


