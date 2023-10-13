package cn.Hlmove.dao;

import cn.Hlmove.entities.TUserReceiveEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TUserReceiveDao {

    @Insert("insert into t_user_receive(userid,receivename,province,tel,address) " +
            "values(#{userid},#{receivename},#{province},#{tel},#{address})")
    int insert(TUserReceiveEntity entity);

    @Update("update t_user_receive set receivename=#{receivename},province=#{province}" +
            ",tel=#{tel},address=#{address} where receiveid=#{receiveid}")
    int update(TUserReceiveEntity entity);

    @Delete("delete from t_user_receive where receiveid=#{receiveid}")
    int delete(int receiveid);

    @Select("select * from t_user_receive")
    List<TUserReceiveEntity> select();

    //分页支持
    @Select("select * from t_user_receive limit #{offset}, #{length}")
    List<TUserReceiveEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_user_receive")
    int selectRecordCount();

    @Select("select * from t_user_receive where receiveid=#{receiveid}")
    TUserReceiveEntity selectById(int receiveid);
    //以上都未修改完

    @Select("select * from t_user_receive where userid=#{userid} and isdefault=1")
    TUserReceiveEntity selectDefaultByUserId(int userid);

}
