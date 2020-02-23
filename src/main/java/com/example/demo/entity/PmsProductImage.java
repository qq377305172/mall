package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品图片表(PmsProductImage)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:16
 */
@Getter
@Setter
@ToString
public class PmsProductImage implements Serializable {
    private static final long serialVersionUID = 967656399948314199L;
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
    private Long productId;
    /**
     * 图片名称
     */
    @NotBlank(message = "图片名称不能为空")
    private String imgName;
    /**
     * 图片路径
     */
    @NotBlank(message = "图片url不能为空")
    private String imgUrl;


}