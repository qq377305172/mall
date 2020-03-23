package com.example.demo.service.impl;

import com.example.demo.dao.OmsOrderDao;
import com.example.demo.dao.PmsSkuInfoDao;
import com.example.demo.entity.OmsOrder;
import com.example.demo.entity.PmsSkuInfo;
import com.example.demo.service.OrderService;
import com.example.demo.util.RedisUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

/**
 * @author Admin
 * @title: OrderServiceImpl
 * @projectName demo
 * @description: TODO
 * @date 2020/3/21 18:06
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OmsOrderDao omsOrderDao;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private PmsSkuInfoDao pmsSkuInfoDao;

    @Override
    public String genTradeNo(Long memberId) {
        Jedis jedis = null;
        try {
            String userTradeNoKey = "user:" + memberId + ":tradeNo";
            UUID uuid = UUID.randomUUID();
            jedis = redisUtil.getJedis();
            jedis.setex(userTradeNoKey, 60 * 30, uuid.toString());
            return uuid.toString();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean checkTradeNo(Long memberId, String tradeNo) {
        Jedis jedis = null;
        try {
            String userTradeNoKey = "user:" + memberId + ":tradeNo";
            jedis = redisUtil.getJedis();
            String s = jedis.get(userTradeNoKey);
            return null != tradeNo && tradeNo.equals(s);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean delTradeNo(Long memberId, String tradeNo) {
        Jedis jedis = null;
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            jedis = redisUtil.getJedis();

            String userTradeNoKey = "user:" + memberId + ":tradeNo";

            Long eval = (Long) jedis.eval(script, Collections.singletonList(userTradeNoKey),
                    Collections.singletonList(tradeNo));

            return eval != null && eval == 1;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean checkPrice(Long productSkuId, BigDecimal price) {
        Example example = new Example(PmsSkuInfo.class);
        example.createCriteria().andEqualTo("id", productSkuId).andEqualTo("price", price);
        return pmsSkuInfoDao.selectCountByExample(example) == 1;
    }

    @Override
    public int saveOrder(OmsOrder omsOrder) {
        return omsOrderDao.insertSelective(omsOrder);
    }
}
