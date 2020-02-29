package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * spu销售属性值(PmsProductSaleAttrValue)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:17
 */
@Getter
@Setter
@ToString
public class PmsProductSaleAttrValue implements Serializable {
    private static final long serialVersionUID = -56982517749763014L;
    /**
     * id
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
     * 销售属性id
     */
    private Long saleAttrId;
    /**
     * 销售属性值名称
     */
    private String saleAttrValueName;

    /**
     * 是否选中
     */
    @Transient
    private Integer isChecked;
}