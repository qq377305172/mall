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
 * 会员收货地址表(UmsMemberReceiveAddress)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:19
 */
@Getter
@Setter
@ToString
public class UmsMemberReceiveAddress implements Serializable {
    private static final long serialVersionUID = -85678050997474176L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    /**
     * 收货人名称
     */
    private String name;

    private String phoneNumber;
    /**
     * 是否为默认
     */
    private Integer defaultStatus;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 省份/直辖市
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址(街道)
     */
    private String detailAddress;


}