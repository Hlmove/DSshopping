package cn.Hlmove.dao;

import cn.Hlmove.entities.TOrderCartEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TOrderCartDao {

    @Insert("insert into t_order_cart(userid,productid,productName,productPrice,productNum,buytime) values(#{userid},#{productid},#{productName},#{productPrice},#{productNum},#{buytime})")
    int insert(TOrderCartEntity entity);

    @Update("update t_order_cart set productid=#{productid},productName=#{productName},productPrice=#{productPrice},productNum=#{productNum} where cartid=#{cartid}")
    int update(TOrderCartEntity entity);

    @Delete("delete from t_order_cart where cartid=#{cartid}")
    int delete(int cartid);

    @Delete("delete from t_order_cart where userid=#{userid}")
    int deleteByUserid(int userid);

    @Select("select * from t_order_cart")
    List<TOrderCartEntity> select();

    @Select("select * from t_order_cart where userid=#{userid}")
    List<TOrderCartEntity> selectByUserid(int userid);

    //分页支持
    @Select("select * from t_order_cart limit #{offset}, #{length}")
    List<TOrderCartEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_order_cart")
    int selectRecordCount();

    @Select("select * from t_order_cart where cartid=#{cartid}")
    TOrderCartEntity selectById(int cartid);

}
