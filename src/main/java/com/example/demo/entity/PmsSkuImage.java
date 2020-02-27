package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 库存单元图片表(PmsSkuImage)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:17
 */
@Getter
@Setter
@ToString
public class PmsSkuImage implements Serializable {
    private static final long serialVersionUID = 119233424297881626L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 图片名称（冗余）
     */
    private String imgName;
    /**
     * 图片路径(冗余)
     */
    private String imgUrl;
    /**
     * 商品图片id
     */
    private Long spuImgId;
    /**
     * 是否默认
     */
    private String isDefault;



}