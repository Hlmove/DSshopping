package cn.Hlmove.dao;

import cn.Hlmove.entities.TOrderOrderitemsEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TOrderOrderitemsDao {

    
        @Insert("insert into t_order_orderitems(orderid, productid, productName, productPrice, buynum)" +
                " values (#{orderid},#{productid},#{productName},#{productPrice},#{ buynum})")
        int insert(TOrderOrderitemsEntity entity);
        //@Update("update t_order_orderitems set orderid=#{orderid},productName=#{productName},productPrice=#{productPrice},buynum=#{buynum}" +
        //        "where orderitemId=#{orderitemId}")
        //int update(TOrderOrderitemsEntity entity);
        @Delete("delete from t_order_orderitems where orderitemId=#{orderitemId}")
        int delete(int orderitemId);

        @Select("select *from t_order_orderitems")
        List<TOrderOrderitemsEntity> select();

        @Select("select *from t_order_orderitems where orderitemId=#{orderitemId}")
        TOrderOrderitemsEntity selectById(int orderitemId);
    

}
