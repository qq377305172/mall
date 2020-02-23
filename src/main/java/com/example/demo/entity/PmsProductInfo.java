package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * (PmsProductInfo)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:16
 */
@Getter
@Setter
@ToString
public class PmsProductInfo implements Serializable {
    private static final long serialVersionUID = 691673977048624442L;
    /**
     * 商品id
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;
    /**
     * 商品描述(后台简述）
     */
    private String description;
    /**
     * 三级分类id
     */
    @NotNull(message = "三级分类id不能为空")
    private Long catalog3Id;
    /**
     * 品牌id
     */
    private Long tmId;

    /**
     * 商品图片集合
     */
    @Transient
    private List<PmsProductImage> spuImageList;
    /**
     * 商品销售属性集合
     */
    @Transient
    private List<PmsProductSaleAttr> spuSaleAttrList;

}