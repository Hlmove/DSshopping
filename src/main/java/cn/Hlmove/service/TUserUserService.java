package cn.Hlmove.service;

import cn.Hlmove.dao.TUserUserDao;
import cn.Hlmove.entities.TUserUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层
//@Component                      //纳入Spring管理（ new TAdminAdminService()==>Spring容器 ）
@Service                        //语义化注解
public class TUserUserService {

    @Autowired
    TUserUserDao dao;

    public List<TUserUserEntity> list(){
        List<TUserUserEntity> entities = dao.select();
        return entities;
    }

    public List<TUserUserEntity> list(int pageIndex, int pageSize){
        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TUserUserEntity> entities = dao.selectPager(offset, length);
        return entities;
    }

    public int selectRecordCount(){
        return dao.selectRecordCount();
    }

    public int insert(TUserUserEntity entity){
        return dao.insert(entity);
    }

    public TUserUserEntity findById(int id){
        TUserUserEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TUserUserEntity entity){
        int result = dao.update(entity);
        return result;
    }

    public int remove(int id){
        int result = dao.delete(id);
        return result;
    }

    public TUserUserEntity login(String username, String userpwd){
        TUserUserEntity user = dao.selectByUsernameAndUserpwd(username, userpwd);
        return user;
    }
}
