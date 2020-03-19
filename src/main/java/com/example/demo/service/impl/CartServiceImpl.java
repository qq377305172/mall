package com.example.demo.service.impl;

import com.example.demo.dao.OmsCartItemDao;
import com.example.demo.entity.OmsCartItem;
import com.example.demo.service.CartService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 * @title: CartServiceImpl
 * @projectName demo
 * @description: TODO
 * @date 2020/3/7 13:39
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private OmsCartItemDao omsCartItemDao;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public OmsCartItem getCartByMemberIdAndSkuId(Long memberId, Long skuId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        omsCartItem.setProductSkuId(skuId);
        return omsCartItemDao.queryOne(omsCartItem);
    }

    @Override
    public int updateCart(OmsCartItem omsCartItemFromDb) {
        return omsCartItemDao.update(omsCartItemFromDb);
    }

    @Override
    public int addCart(OmsCartItem omsCartItem) {
        return omsCartItemDao.insert(omsCartItem);
    }

    @Override
    public void sync(Long memberId) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        List<OmsCartItem> omsCartItems = omsCartItemDao.queryAll(omsCartItem);
        if (CollectionUtils.isEmpty(omsCartItems)) {
            return;
        }
        Jedis jedis = redisUtil.getJedis();
        Map<String, String> value = new HashMap<>();
        for (OmsCartItem cartItem : omsCartItems) {
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
            value.put(cartItem.getProductSkuId().toString(), JsonUtil.obj2Json(cartItem));
        }
        jedis.del("user:" + memberId + ":cart");
        jedis.hmset("user:" + memberId + ":cart", value);
        jedis.close();
    }

    @Override
    public List<OmsCartItem> getCartByMemberId(Long memberId) {
        List<OmsCartItem> omsCartItemList = new ArrayList<>();
        try (Jedis jedis = redisUtil.getJedis()) {
            List<String> hvals = jedis.hvals("user:" + memberId + ":cart");
            if (!CollectionUtils.isEmpty(hvals)) {
                for (String hval : hvals) {
                    OmsCartItem omsCartItem = JsonUtil.json2Obj(hval, OmsCartItem.class);
                    assert omsCartItem != null;
                    omsCartItem.setTotalPrice(omsCartItem.getPrice().multiply(new BigDecimal(omsCartItem.getQuantity())));
                    omsCartItemList.add(omsCartItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return omsCartItemList;
//        OmsCartItem omsCartItem = new OmsCartItem();
//        omsCartItem.setMemberId(memberId);
//        return omsCartItemDao.queryAll(omsCartItem);
    }

    @Override
    public void checkCart(Long memberId, long skuId, int isChecked) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setMemberId(memberId);
        omsCartItem.setProductSkuId(skuId);
        omsCartItem.setIsChecked(isChecked);
        int update = omsCartItemDao.updateCheckedStatus(omsCartItem);
        if (update == 1) {
            //更新成功,刷新缓存
            sync(memberId);
        }
    }
}
