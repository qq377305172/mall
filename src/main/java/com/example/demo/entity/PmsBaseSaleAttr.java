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
 * (PmsBaseSaleAttr)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:15
 */
@Getter
@Setter
@ToString
public class PmsBaseSaleAttr implements Serializable {
    private static final long serialVersionUID = -39202054268353713L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 销售属性名称
     */
    private String name;


}