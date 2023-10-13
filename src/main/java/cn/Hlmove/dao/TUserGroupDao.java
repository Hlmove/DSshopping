package cn.Hlmove.dao;


import cn.Hlmove.entities.TUserGroupEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;








    @Component
    @Mapper
    public interface TUserGroupDao {

        @Insert("insert into t_user_group(usergroupname) values(#{usergroupname})")
        int insert(TUserGroupEntity entity);

        @Update("update t_user_group set usergroupname=#{usergroupname} where usergroupid=#{usergroupid}")
        int update(TUserGroupEntity entity);

        @Delete("delete from t_user_group where usergroupid=#{usergroupid}")
        int delete(int usergroupid);

        @Select("select * from t_user_group")
        List<TUserGroupEntity> select();

        @Select("select * from t_user_group where usergroupid=#{usergroupid}")
        TUserGroupEntity selectById(int usergroupid);

        //分页支持
        //@Select("select * from t_admin_admin limit #{offset}, #{length}")
        @Select("select * from t_user_group limit #{offset}, #{length}")
        List<TUserGroupEntity> selectPager(int offset, int length);

        //分页支持--获取总记录数
        @Select("select count(1) from t_user_group")
        int selectRecordCount();

    }


