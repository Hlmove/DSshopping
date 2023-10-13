package cn.Hlmove.dao;

import cn.Hlmove.entities.TAdminGroupEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员分组信息表 数据访问层
 */
@Component
@Mapper
public interface TAdminGroupDao {

    @Insert("insert into t_admin_group(admingroupname) values(#{admingroupname})")
    int insert(TAdminGroupEntity entity);

    @Update("update t_admin_group set admingroupname=#{admingroupname} where admingroupid=#{admingroupid}")
    int update(TAdminGroupEntity entity);

    @Delete("delete from t_admin_group where admingroupid=#{admingroupid}")
    int delete(int admingroupid);

    @Select("select * from t_admin_group")
    List<TAdminGroupEntity> select();

    @Select("select * from t_admin_group where admingroupid=#{admingroupid}")
    TAdminGroupEntity selectById(int admingroupid);

    //分页支持
    //@Select("select * from t_admin_admin limit #{offset}, #{length}")
    @Select("select * from t_admin_group limit #{offset}, #{length}")
    List<TAdminGroupEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_admin_group")
    int selectRecordCount();

}
