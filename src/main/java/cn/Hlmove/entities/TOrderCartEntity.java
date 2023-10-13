package cn.Hlmove.entities;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TOrderCartEntity {
    /**
     * t_order_cart
     */
    private Integer cartid;
    private Integer userid;
    private Integer productid;
    private String productName;
    private Double productPrice;
    private Integer productNum;
    private Timestamp buytime;

}