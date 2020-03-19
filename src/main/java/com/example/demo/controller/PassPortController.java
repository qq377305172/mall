package com.example.demo.controller;

import com.example.demo.entity.UmsMember;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.RedisUtil;
import com.jfinal.kit.StrKit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 * @title: PassPortController
 * @projectName demo
 * @description: TODO
 * @date 2020/3/12 21:02
 */
@Controller
public class PassPortController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/verify")
    public Map<String, Object> verify(String token, String currentIp) {
        Map<String, Object> userMap = JwtUtil.decode(token, "gmall", currentIp);
        if (null == userMap) {
            return null;
        }
        userMap.put("status", "success");
        return userMap;
//        return JsonUtil.map2Json(userMap);
    }

    /**
     * 登录方法
     *
     * @param umsMember 会员信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(UmsMember umsMember, HttpServletRequest request) {
        String token;
        UmsMember login = userService.login(umsMember);
        if (null == login) {
            token = "failed";
            return token;
        }
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("memberId", login.getId());
        userMap.put("nickName", login.getNickname());
        String ip = request.getHeader("x-forwarded-for");
        ip = ip == null ? request.getRemoteAddr() : ip;
        ip = ip == null ? "127.0.0.1" : ip;
        token = JwtUtil.encode("gmall", userMap, ip);
        userService.saveToken(token, login.getId());
        return token;
    }

    /**
     * 跳转登录页面
     *
     * @param returnUrl 登陆成功跳转的页面
     * @param map       页面写值
     * @return 登录页面
     */
    @RequestMapping("/toLogin")
    public String tologin(String returnUrl, ModelMap map) {
        map.put("returnUrl", returnUrl);
        return "login";
    }
}
