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
 * 库存单元表(PmsSkuInfo)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:18
 */
@Getter
@Setter
@ToString
public class PmsSkuInfo implements Serializable {
    private static final long serialVersionUID = 846179689657575204L;
    /**
     * 库存id(itemID)
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 价格
     */
    private Object price;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 商品规格描述
     */
    private String skuDesc;

    private Object weight;
    /**
     * 品牌(冗余)
     */
    private Long tmId;
    /**
     * 三级分类id（冗余)
     */
    private Long catalog3Id;
    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;


}