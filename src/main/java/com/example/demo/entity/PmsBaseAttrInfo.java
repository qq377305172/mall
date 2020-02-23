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
 * 属性表(PmsBaseAttrInfo)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:13
 */
@Getter
@Setter
@ToString
public class PmsBaseAttrInfo implements Serializable {
    private static final long serialVersionUID = -60694360373169866L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 属性名称
     */
    @NotBlank(message = "属性名非法")
    private String attrName;
    @NotNull(message = "类别id不能为空")
    private Long catalog3Id;
    /**
     * 启用：1 停用：0
     */
    private String isEnabled;
    @Transient
    private List<PmsBaseAttrValue> attrValueList;


}