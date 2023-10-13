package cn.Hlmove.entities;

import lombok.Data;

/*
 * 商品信息表实体类
 * */
@Data
public class TProductProductEntity {
    private Integer productid;
    private Integer classid;
    private Integer brandid;
    private String productname;
    private String smallimg;
    private String bigimg;
    private Double price;
    private String description;
    private String brandname;
    private String classname;
}

