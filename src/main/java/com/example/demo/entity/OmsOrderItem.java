package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 订单中所包含的商品(OmsOrderItem)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:12
 */
@Getter
@Setter
@ToString
public class OmsOrderItem implements Serializable {
    private static final long serialVersionUID = -72967097242270588L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单编号
     */
    private String orderSn;

    private Long productId;

    private String productPic;

    private String productName;

    private String productBrand;

    private String productSn;
    /**
     * 销售价格
     */
    private Double productPrice;
    /**
     * 购买数量
     */
    private Integer productQuantity;
    /**
     * 商品sku编号
     */
    private Long productSkuId;
    /**
     * 商品sku条码
     */
    private String productSkuCode;
    /**
     * 商品分类id
     */
    private Long productCategoryId;
    /**
     * 商品的销售属性
     */
    private String sp1;

    private String sp2;

    private String sp3;
    /**
     * 商品促销名称
     */
    private String promotionName;
    /**
     * 商品促销分解金额
     */
    private Double promotionAmount;
    /**
     * 优惠券优惠分解金额
     */
    private Double couponAmount;
    /**
     * 积分优惠分解金额
     */
    private Double integrationAmount;
    /**
     * 该商品经过优惠后的分解金额
     */
    private Double realAmount;

    private Integer giftIntegration;

    private Integer giftGrowth;
    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;


}