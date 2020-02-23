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
 * 一级分类表(PmsBaseCatalog1)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:13
 */
@Getter
@Setter
@ToString
public class PmsBaseCatalog1 implements Serializable {
    private static final long serialVersionUID = 909502961368873870L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 分类名称
     */
    private String name;


}