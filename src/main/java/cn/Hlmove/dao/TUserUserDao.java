package cn.Hlmove.dao;

import cn.Hlmove.entities.TUserUserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TUserUserDao {

    @Insert("insert into t_user_user(usergroupid,username,userpwd,gender,regtime,modifytime,description)" +
            " values (#{usergroupid},#{username},#{userpwd},#{gender},#{regtime},#{modifytime},#{description})")
    int insert(TUserUserEntity entity);

    @Update("update t_user_user set usergroupid=#{usergroupid},username=#{username},userpwd=#{userpwd},gender=#{gender}" +
            ",regtime=#{regtime},modifytime=#{modifytime},description=#{description} where userid=#{userid}")
    int update(TUserUserEntity entity);
    @Delete("delete from t_user_user where userid=#{userid}")
    int delete(int userid);

    //@Select("select * from t_admin_admin")
    @Select("select A.*, B.usergroupname from t_user_user A inner join t_user_group B on A.usergroupid = B.usergroupid")
    List<TUserUserEntity> select();

    @Select("select *from t_user_user where userid=#{userid}")
    TUserUserEntity selectById(int userid);

    //分页支持
    @Select("select A.*, B.usergroupname from t_user_user A inner join t_user_group B on A.usergroupid = B.usergroupid limit #{offset}, #{length}")
    List<TUserUserEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_user_user")
    int selectRecordCount();

    @Select("select * from t_user_user where username=#{username} and userpwd=#{userpwd}")
    TUserUserEntity selectByUsernameAndUserpwd(String username, String userpwd);


}
