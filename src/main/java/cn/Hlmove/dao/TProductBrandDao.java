package cn.Hlmove.dao;

import cn.Hlmove.entities.TProductBrandEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TProductBrandDao {

    @Insert("insert into t_product_brand(brandname) values(#{brandname})")
    int insert(TProductBrandEntity entity);

    @Update("update t_product_brand set brandname=#{brandname} where brandid=#{brandid}")
    int update(TProductBrandEntity entity);

    @Delete("delete from t_product_brand where brandid=#{brandid}")
    int delete(int brandid);

    @Select("select * from t_product_brand")
    List<TProductBrandEntity> select();

    //分页支持
    @Select("select * from t_product_brand limit #{offset}, #{length}")
    List<TProductBrandEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_product_brand")
    int selectRecordCount();

    @Select("select * from t_product_brand where brandid=#{brandid}")
    TProductBrandEntity selectById(int brandid);

}
