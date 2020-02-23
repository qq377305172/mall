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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSaleAttrId() {
        return saleAttrId;
    }

    public void setSaleAttrId(Long saleAttrId) {
        this.saleAttrId = saleAttrId;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }

}