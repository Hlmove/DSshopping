package cn.Hlmove.dao;

import cn.Hlmove.entities.TProductProductEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TProductProductDao {

    @Insert("insert into t_product_product(classid, brandid, productname, smallimg, bigimg, price, description) " +
            "values(#{classid}, #{brandid}, #{productname}, #{smallimg}, #{bigimg}, #{price}, #{description})")
    int insert(TProductProductEntity entity);

    @Update("update t_product_product set classid=#{classid}, productname=#{productname}, " +
            "smallimg=#{smallimg}, bigimg=#{bigimg}, price=#{price}, description=#{description}  " +
            " where productid=#{productid}")
    int update(TProductProductEntity entity);


    @Select("select * from t_product_product")
    List<TProductProductEntity> select();

    @Delete("delete from t_product_product where productid=#{productid}")
    int delete(int productid);

    //分页支持
    @Select("select * from v_product_product limit #{offset}, #{length}")
    List<TProductProductEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_product_product")
    int selectRecordCount();

    @Select("select * from t_product_product where productid=#{productid}")
    TProductProductEntity selectById(int productid);

}
