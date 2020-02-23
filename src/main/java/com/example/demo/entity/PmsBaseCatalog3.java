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
 * (PmsBaseCatalog3)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:15
 */
@Getter
@Setter
@ToString
public class PmsBaseCatalog3 implements Serializable {
    private static final long serialVersionUID = 665028525738421732L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 三级分类名称
     */
    private String name;
    /**
     * 二级分类编号
     */
    private Long catalog2Id;


}