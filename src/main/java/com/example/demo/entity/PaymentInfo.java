package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

/**
 * 支付信息表(PaymentInfo)实体类
 *
 * @author makejava
 * @since 2020-02-21 15:32:13
 */
@Getter
@Setter
@ToString
public class PaymentInfo implements Serializable {
    private static final long serialVersionUID = -58515027778758516L;
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 对外业务编号
     */
    private String orderSn;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 支付宝交易编号
     */
    private String alipayTradeNo;
    /**
     * 支付金额
     */
    private Double totalAmount;
    /**
     * 交易内容
     */
    private String subject;
    /**
     * 支付状态
     */
    private String paymentStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private Date confirmTime;
    /**
     * 回调信息
     */
    private String callbackContent;

    private Date callbackTime;


}