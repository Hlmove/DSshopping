package cn.Hlmove.dao;

import cn.Hlmove.entities.TProductClassEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员信息表 数据访问层
 */
@Component              //将该类型纳入Spring管理
@Mapper
public interface TProductClassDao {

    @Insert("insert into t_product_class(classname) values(#{classname})")
    int insert(TProductClassEntity entity);

    @Update("update t_product_class set classname=#{classname} where classid=#{classid}")
    int update(TProductClassEntity entity);

    @Delete("delete from t_product_class where classid=#{classid}")
    int delete(int classid);

    @Select("select * from t_product_class")
    List<TProductClassEntity> select();

    //分页支持
    @Select("select * from t_product_class limit #{offset}, #{length}")
    List<TProductClassEntity> selectPager(int offset, int length);

    //分页支持--获取总记录数
    @Select("select count(1) from t_product_class")
    int selectRecordCount();

    @Select("select * from t_product_class where classid=#{classid}")
    TProductClassEntity selectById(int classid);

}
