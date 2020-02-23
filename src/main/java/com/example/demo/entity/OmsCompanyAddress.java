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
 * 公司收发货地址表(OmsCompanyAddress)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:11
 */
@Getter
@Setter
@ToString
public class OmsCompanyAddress implements Serializable {
    private static final long serialVersionUID = -11451700496201218L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 默认发货地址：0->否；1->是
     */
    private Integer sendStatus;
    /**
     * 是否默认收货地址：0->否；1->是
     */
    private Integer receiveStatus;
    /**
     * 收发货人姓名
     */
    private String name;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 省/直辖市
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址
     */
    private String detailAddress;


}