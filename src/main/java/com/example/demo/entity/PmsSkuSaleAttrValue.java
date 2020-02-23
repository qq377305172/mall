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
 * sku销售属性值(PmsSkuSaleAttrValue)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:18
 */
@Getter
@Setter
@ToString
public class PmsSkuSaleAttrValue implements Serializable {
    private static final long serialVersionUID = 907982796270855604L;
    /**
     * id
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 库存单元id
     */
    private Long skuId;
    /**
     * 销售属性id（冗余)
     */
    private Long saleAttrId;
    /**
     * 销售属性值id
     */
    private Long saleAttrValueId;
    /**
     * 销售属性名称(冗余)
     */
    private String saleAttrName;
    /**
     * 销售属性值名称(冗余)
     */
    private String saleAttrValueName;


}