package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Admin
 * @title: CommonUtil
 * @projectName demo
 * @description: TODO
 * @date 2020/3/23 20:17
 */
public class CommonUtil {
    public static Long getMemberId(HttpServletRequest request) {
        return Integer.valueOf(String.valueOf(request.getAttribute("memberId"))).longValue();
    }
}
