package com.example.demo.controller;

import com.example.demo.annotations.LoginRequired;
import com.example.demo.entity.OmsCartItem;
import com.example.demo.entity.PmsSkuInfo;
import com.example.demo.entity.UmsMember;
import com.example.demo.service.AttrService;
import com.example.demo.service.CartService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JsonUtil;
import com.jfinal.kit.StrKit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Admin
 * @title: CartController
 * @projectName demo
 * @description: TODO
 * @date 2020/3/3 16:34
 */
@Controller
public class CartController {
    @Resource
    private AttrService attrService;
    @Resource
    private CartService cartService;

    @ResponseBody
    @LoginRequired(loginSuccess = true)
    @RequestMapping("/toTrade")
    public String toTrade(HttpServletRequest request,
                          HttpServletResponse response) {
        String memberId = String.valueOf(request.getAttribute("memberId"));
        String nickName = String.valueOf(request.getAttribute("nickName"));
        return memberId + nickName;
    }

    @LoginRequired(loginSuccess = false)
    @RequestMapping("/checkCart")
    public String checkCart(@RequestParam("isChecked") int isChecked, @RequestParam("skuId") long skuId, HttpServletRequest request,
                            HttpServletResponse response, ModelMap modelMap) {
        Long memberId = 1L;
        if (memberId == null) {

        } else {
            cartService.checkCart(memberId, skuId, isChecked);
        }
        List<OmsCartItem> omsCartItemList = cartService.getCartByMemberId(memberId);
        //被勾选的商品总额
        BigDecimal totalAmount = CalculationTotalAmount(omsCartItemList);
        modelMap.put("totalAmount", totalAmount);
        modelMap.put("cartList", omsCartItemList);
        return "cartListInner";
    }

    @LoginRequired(loginSuccess = false)
    @RequestMapping("/cartList")
    public String cartList(HttpServletRequest request,
                           HttpServletResponse response, ModelMap modelMap, UmsMember umsMember) {
        List<OmsCartItem> omsCartItemList = null;
        Long memberId = umsMember.getId();
        if (null == memberId) {
            //未登录,查询缓存
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if (StrKit.notBlank(cartListCookie)) {
                omsCartItemList = JsonUtil.json2list(cartListCookie, OmsCartItem.class);
            }
        } else {
            //已登录,查询数据库
            omsCartItemList = cartService.getCartByMemberId(memberId);
        }
        modelMap.put("cartList", omsCartItemList);
        //被勾选的商品总额
        BigDecimal totalAmount = CalculationTotalAmount(omsCartItemList);
        modelMap.put("totalAmount", totalAmount);
        return "cartList";
    }

    private BigDecimal CalculationTotalAmount(List<OmsCartItem> omsCartItemList) {
        BigDecimal totalAmount = new BigDecimal(0);
        if (null == omsCartItemList)
            return totalAmount;
        for (OmsCartItem omsCartItem : omsCartItemList) {
            if (omsCartItem.getIsChecked() == 1)
                totalAmount = totalAmount.add(omsCartItem.getPrice());
        }
        return totalAmount;
    }

    @LoginRequired(loginSuccess = false)
    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam(name = "memberId", defaultValue = "1") Long memberId, @RequestParam(name = "quantity") Integer quantity, @RequestParam("skuId") Long skuId, HttpServletRequest request,
                            HttpServletResponse response) {
        PmsSkuInfo pmsSkuInfo = attrService.getSkuById(skuId);
        if (null == pmsSkuInfo)
            return "redirect:/toSuccess";
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setCreateDate(new Date());
        omsCartItem.setDeleteStatus(0);
        omsCartItem.setModifyDate(new Date());
        omsCartItem.setPrice(pmsSkuInfo.getPrice());
        omsCartItem.setProductAttr("");
        omsCartItem.setProductBrand("");
        omsCartItem.setProductCategoryId(pmsSkuInfo.getCatalog3Id());
        omsCartItem.setProductId(pmsSkuInfo.getProductId());
        omsCartItem.setProductName(pmsSkuInfo.getSkuName());
        omsCartItem.setProductPic(pmsSkuInfo.getSkuDefaultImg());
        omsCartItem.setProductSkuCode("");
        omsCartItem.setProductSkuId(skuId);
        omsCartItem.setQuantity(quantity);
        if (memberId == null) {
            //未登录
            //将购物车信息存入cookie
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            List<OmsCartItem> omsCartItems = new ArrayList<>();
            if (StrKit.notBlank(cartListCookie)) {
                omsCartItems = JsonUtil.json2list(cartListCookie, OmsCartItem.class);
                handle(omsCartItem, omsCartItems);
            } else {
                omsCartItems.add(omsCartItem);
            }
            CookieUtil.setCookie(request, response, "cartListCookie", JsonUtil.collection2Json(omsCartItems), 60 * 60 * 24 * 3, true);
        } else {
            //已登录
            //将购物车信息存入数据库和缓存
            //根据当前用户id和商品skuId查询购物车
            OmsCartItem omsCartItemFromDB = cartService.getCartByMemberIdAndSkuId(memberId, skuId);
            if (null != omsCartItemFromDB) {
                //购物车中存在
                omsCartItemFromDB.setQuantity(omsCartItemFromDB.getQuantity() + quantity);
                cartService.updateCart(omsCartItemFromDB);
            } else {
                //购物车中不存在
                omsCartItem.setMemberId(memberId);
                cartService.addCart(omsCartItem);
            }
            //更新缓存
            cartService.sync(memberId);
        }
        return "redirect:/toSuccess";
    }

    private void handle(OmsCartItem omsCartItem, List<OmsCartItem> omsCartItems) {
        boolean flag = false;
        for (OmsCartItem item : omsCartItems) {
            if (item.getProductSkuId().equals(omsCartItem.getProductSkuId())) {
                item.setQuantity(item.getQuantity() + omsCartItem.getQuantity());
                item.setPrice(item.getPrice().add(omsCartItem.getPrice()));
                flag = true;
            }
        }
        if (!flag)
            omsCartItems.add(omsCartItem);
    }

    @LoginRequired(loginSuccess = false)
    @RequestMapping("/toSuccess")
    public String toSuccess() {
        return "success";
    }
}
