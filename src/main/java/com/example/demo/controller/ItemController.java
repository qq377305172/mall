package com.example.demo.controller;

import com.example.demo.entity.PmsSkuInfo;
import com.example.demo.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Admin
 * @title: IndexController
 * @projectName demo
 * @description: TODO
 * @date 2020/2/26 16:51
 */
@Controller
public class ItemController {
    @Resource
    private SkuService skuService;

    @GetMapping("/{skuId}.html")
    public ModelAndView item(@PathVariable Long skuId, ModelAndView modelAndView) {
        PmsSkuInfo pmsSkuInfo = skuService.getSkuInfo(skuId);
        modelAndView.addObject("skuInfo", pmsSkuInfo);
        modelAndView.setViewName("item");
        return modelAndView;
    }

}
