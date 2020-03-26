package com.example.demo.service;

import com.example.demo.entity.PaymentInfo;

/**
 * @author Admin
 * @title: PaymentService
 * @projectName demo
 * @description: TODO
 * @date 2020/3/25 20:52
 */
public interface PaymentService {
    Long save(PaymentInfo paymentInfo);

    int update(PaymentInfo paymentInfo);
}
