package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * (PmsProductSaleAttr)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:17
 */
@Getter
@Setter
@ToString
public class PmsProductSaleAttr implements Serializable {
    private static final long serialVersionUID = 661544460530219598L;
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
     * 销售属性名称(冗余)
     */
    private String saleAttrName;
    /**
     * 销售属性值
     */
    @Transient
    private List<PmsProductSaleAttrValue> spuSaleAttrValueList;


}