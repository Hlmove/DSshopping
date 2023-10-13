package cn.Hlmove.entities;

import lombok.Data;

/**
 * t_order_orderitems
 */
@Data
public class TOrderOrderitemsEntity {

    private Integer orderitemId;
    private Integer orderid;
    private Integer productid;
    private String productName;
    private Double productPrice;
    private Integer buynum;
}
