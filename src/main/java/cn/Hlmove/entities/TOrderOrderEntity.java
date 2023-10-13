package cn.Hlmove.entities;

import lombok.Data;

import java.sql.Timestamp;

/*
 * 订单信息表实体类
 * */
@Data
public class TOrderOrderEntity {
    private int orderid;
    private Integer userid;
    private String receivename;
    private String province;
    private String address;
    private String tel;
    private Double ordersum;
    private Timestamp ordertime;
}
