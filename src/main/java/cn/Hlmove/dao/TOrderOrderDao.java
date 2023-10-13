package cn.Hlmove.dao;

import cn.Hlmove.entities.TOrderOrderEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TOrderOrderDao {
    @Insert("insert into t_order_order(orderid, userid,receivename, province, address, tel,ordersum,ordertime)" +
            " values (#{orderid},#{userid},#{receivename},#{province},#{address},#{tel},#{ordersum},#{ordertime})")
int insert(TOrderOrderEntity entity);
    //@Update("update t_order_order set userid=#{userid},receiveid=#{receiveid},ordersum=#{ordersum},ordertime=#{ordertime}" +
    //        "where orderid=#{orderid}")
    //int update(TOrderOrderEntity entity);
    @Delete("delete from t_order_order where orderid=#{orderid}")
    int delete(String orderid);

    @Select("select *from t_order_order")
    List<TOrderOrderEntity> select();

    @Select("select *from t_order_order where orderid=#{orderid}")
    TOrderOrderEntity selectById(String orderid);

    //分页支持
    //@Select("select * from t_admin_admin limit #{offset}, #{length}")
    @Select("select * from t_order_order limit #{offset}, #{length}")
    List<TOrderOrderEntity> selectPager(int offset, int length);


    //分页支持--获取总记录数
    @Select("select count(1) from t_order_order")
    int selectRecordCount();



    @Update("update t_order_order set receivename=#{receivename}, province=#{province}, " +
            "address=#{address}, tel=#{tel} " +
            " where orderid=#{orderid}")
    int update(TOrderOrderEntity entity);
























}
