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
 * sku平台属性值关联表(PmsSkuAttrValue)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:17
 */
@Getter
@Setter
@ToString
public class PmsSkuAttrValue implements Serializable {
    private static final long serialVersionUID = -73380842540116696L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 属性id（冗余)
     */
    private Long attrId;
    /**
     * 属性值id
     */
    private Long valueId;
    /**
     * skuid
     */
    private Long skuId;


}