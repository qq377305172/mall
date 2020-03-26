package com.example.demo.service.impl;

import com.example.demo.dao.PaymentInfoDao;
import com.example.demo.entity.PaymentInfo;
import com.example.demo.service.PaymentService;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author Admin
 * @title: PaymentServiceImpl
 * @projectName demo
 * @description: TODO
 * @date 2020/3/25 20:52
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentInfoDao paymentInfoDao;

    @Override
    public Long save(PaymentInfo paymentInfo) {
        int i = paymentInfoDao.insertSelective(paymentInfo);
        return paymentInfo.getId();
    }

    @Override
    public int update(PaymentInfo paymentInfo) {
        Example example = new Example(PaymentInfo.class);
        example.createCriteria().andEqualTo("orderSn", paymentInfo.getOrderSn());
        return paymentInfoDao.updateByExampleSelective(paymentInfo, example);
    }
}
