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
 * (PmsBaseCatalog2)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:15
 */
@Getter
@Setter
@ToString
public class PmsBaseCatalog2 implements Serializable {
    private static final long serialVersionUID = 587853689749146841L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 二级分类名称
     */
    private String name;
    /**
     * 一级分类编号
     */
    private Integer catalog1Id;


}