package cn.Hlmove.dao;

import cn.Hlmove.entities.TAdminAdminEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 * @author Hlmove
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TAdminAdminDao {

    @Insert("insert into t_admin_admin(admingroupid, adminname, adminpwd, gender, regtime, modifytime, description) " +
            "values(#{admingroupid}, #{adminname}, #{adminpwd}, #{gender}, #{regtime}, #{modifytime}, #{description})")
    int insert(TAdminAdminEntity entity);

    @Update("update t_admin_admin set admingroupid=#{admingroupid}, adminname=#{adminname}, " +
            "gender=#{gender}, modifytime=#{modifytime}, description=#{description} " +
            " where adminid=#{adminid}")
    int update(TAdminAdminEntity entity);

    @Update("update t_admin_admin set adminpwd=#{adminpwd} where adminname=#{adminname}")
    int updateUserPwd(String adminname, String adminpwd);

    @Delete("delete from t_admin_admin where adminid=#{adminid}")
    int delete(int adminid);

    //@Select("select * from t_admin_admin")
    @Select("select A.*, B.admingroupname from t_admin_admin A inner join t_admin_group B on A.admingroupid = B.admingroupid")
    List<TAdminAdminEntity> select();

    //分页支持
    //@Select("select * from t_admin_admin limit #{offset}, #{length}")
    @Select("select A.*, B.admingroupname from t_admin_admin A inner join t_admin_group B " +
            "on A.admingroupid = B.admingroupid limit #{offset}, #{length}")
    List<TAdminAdminEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_admin_admin")
    int selectRecordCount();


    @Select("select * from t_admin_admin where adminid=#{adminid}")
    TAdminAdminEntity selectById(int adminid);

    @Select("select * from t_admin_admin where adminname=#{adminname} and adminpwd=#{adminpwd}")
    TAdminAdminEntity selectByNameAndPwd(String adminname, String adminpwd);
}
