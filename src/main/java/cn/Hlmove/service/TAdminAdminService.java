package cn.Hlmove.service;

import cn.Hlmove.dao.TAdminAdminDao;
import cn.Hlmove.entities.TAdminAdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层
//@Component                      //纳入Spring管理（ new TAdminAdminService()==>Spring容器 ）
@Service                        //语义化注解
public class TAdminAdminService {

    @Autowired
    TAdminAdminDao dao;

    public TAdminAdminEntity login(String adminName, String adminPwd) {
        //调用Dao层代码，完成验证
        TAdminAdminEntity adminUser = dao.selectByNameAndPwd(adminName, adminPwd);
        return adminUser;
    }

    public int modifyPwd(String adminName, String adminPwd) {
        //调用Dao层代码
        int result = dao.updateUserPwd(adminName, adminPwd);
        return result;
    }

    //管理员列表
    public List<TAdminAdminEntity> list() {
        //调用Dao层代码
        List<TAdminAdminEntity> entities = dao.select();
        return entities;
    }

    //分页支持
    public List<TAdminAdminEntity> list(int pageIndex, int pageSize) {
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex - 1) * pageSize;
        int length = pageSize;

        List<TAdminAdminEntity> entities = dao.selectPager(offset, length);
        return entities;
    }

    //分页支持-返回总记录数
    public int selectRecordCount() {
        return dao.selectRecordCount();
    }

    public int insert(TAdminAdminEntity entity) {
        //dao insert
        return dao.insert(entity);
    }

    public TAdminAdminEntity findById(int id) {
        //dao insert
        TAdminAdminEntity entity = dao.selectById(id);
        return entity;
    }

    public int modify(TAdminAdminEntity entity) {
        //调用Dao层代码
        int result = dao.update(entity);
        return result;
    }

    public int remove(int adminid) {
        //调用Dao层代码
        int result = dao.delete(adminid);
        return result;
    }
}
