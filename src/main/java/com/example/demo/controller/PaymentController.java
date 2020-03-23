package com.example.demo.controller;

import com.example.demo.annotations.LoginRequired;
import com.example.demo.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Admin
 * @title: PaymentController
 * @projectName demo
 * @description: TODO
 * @date 2020/3/23 20:14
 */
@Controller
public class PaymentController {
    @RequestMapping("/payment_select")
    @LoginRequired
    public String payment_select(String tradeNo, BigDecimal totalAmount, HttpServletRequest request) {
        Long memberId = CommonUtil.getMemberId(request);
        return "payment_select";
    }
}
