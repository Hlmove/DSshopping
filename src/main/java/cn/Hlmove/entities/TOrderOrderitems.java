package cn.Hlmove.entities;

import lombok.Data;

/**
 * t_order_orderitems
 */
@Data
public class TOrderOrderitems {

    private Integer orderitemId;
    private Integer orderid;
    private String productName;
    private Double productPrice;
    private Integer buynum;
}
