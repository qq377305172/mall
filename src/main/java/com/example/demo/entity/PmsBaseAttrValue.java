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
 * 属性值表(PmsBaseAttrValue)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:13
 */
@Getter
@Setter
@ToString
public class PmsBaseAttrValue implements Serializable {
    private static final long serialVersionUID = 761812249099570886L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 属性值名称
     */
    @NotBlank(message = "属性值名称非法")
    private String valueName;
    /**
     * 属性id
     */
    @NotNull(message = "属性id不能为空")
    private Long attrId;
    /**
     * 启用：1 停用：0 1
     */
    private String isEnabled;


}