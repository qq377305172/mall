package com.example.demo.controller;

import com.example.demo.controller.vo.PmsSearchCrumb;
import com.example.demo.controller.vo.param.PmsSkuSearchParam;
import com.example.demo.entity.PmsBaseAttrInfo;
import com.example.demo.entity.PmsBaseAttrValue;
import com.example.demo.entity.search.PmsSearchSkuInfo;
import com.example.demo.service.AttrService;
import com.example.demo.service.SearchService;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Admin
 * @title: IndexController
 * @projectName demo
 * @description: TODO
 * @date 2020/3/1 13:49
 */
@Controller
public class SearchController {
    @Resource
    private SearchService searchService;
    @Resource
    private AttrService attrService;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/list.html")
    public ModelAndView list(PmsSkuSearchParam pmsSkuSearchParam, ModelAndView modelAndView) {
        modelAndView.setViewName("list");
        Map<String, Object> map = searchService.list(pmsSkuSearchParam);
        if (null == map) {
            return modelAndView;
        }
        //sku列表
        modelAndView.addObject("skuLsInfoList", map.get("list"));
        String sqlIn = String.valueOf(map.get("set"));
        if (!StringUtils.isEmpty(sqlIn)) {
            List<PmsBaseAttrInfo> pmsBaseAttrInfoList = attrService.getAttrinfosByValueId(sqlIn);
            Long[] delValueIdArr = pmsSkuSearchParam.getValueId();
            if (null != delValueIdArr) {
                //制作面包屑
                List<PmsSearchCrumb> pmsSearchCrumbList = new ArrayList<>();
                for (Long delValueId : delValueIdArr) {
                    Iterator<PmsBaseAttrInfo> iterator = pmsBaseAttrInfoList.iterator();
                    PmsSearchCrumb pmsSearchCrumb = new PmsSearchCrumb();
                    pmsSearchCrumb.setValueId(delValueId);
                    pmsSearchCrumb.setUrlParam(searchService.getUrlParam(pmsSkuSearchParam, delValueId));
                    while (iterator.hasNext()) {
                        PmsBaseAttrInfo pmsBaseAttrInfo = iterator.next();
                        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
                        if (!CollectionUtils.isEmpty(attrValueList)) {
                            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                                Long valueId = pmsBaseAttrValue.getId();
                                if (null != valueId && valueId.equals(delValueId)) {
                                    String valueName = pmsBaseAttrValue.getValueName();
                                    pmsSearchCrumb.setValueName(valueName);
                                    iterator.remove();
                                }
                            }
                        }
                    }
                    pmsSearchCrumbList.add(pmsSearchCrumb);
                }
                modelAndView.addObject("attrValueSelectedList", pmsSearchCrumbList);
            }
            //查询条件属性列表
            modelAndView.addObject("attrList", pmsBaseAttrInfoList);
        }
        String urlParam = searchService.getUrlParam(pmsSkuSearchParam, null);
        //请求参数字符串
        modelAndView.addObject("urlParam", urlParam);
        return modelAndView;
    }
}
