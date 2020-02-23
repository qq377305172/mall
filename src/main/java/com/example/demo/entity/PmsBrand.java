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
 * 品牌表(PmsBrand)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:15
 */
@Getter
@Setter
@ToString
public class PmsBrand implements Serializable {
    private static final long serialVersionUID = -81237209537543944L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /**
     * 首字母
     */
    private String firstLetter;

    private Integer sort;
    /**
     * 是否为品牌制造商：0->不是；1->是
     */
    private Integer factoryStatus;

    private Integer showStatus;
    /**
     * 产品数量
     */
    private Integer productCount;
    /**
     * 产品评论数量
     */
    private Integer productCommentCount;
    /**
     * 品牌logo
     */
    private String logo;
    /**
     * 专区大图
     */
    private String bigPic;
    /**
     * 品牌故事
     */
    private String brandStory;


}