package com.example.demo.service.impl;

import com.example.demo.dao.PmsSkuAttrValueDao;
import com.example.demo.dao.PmsSkuImageDao;
import com.example.demo.dao.PmsSkuInfoDao;
import com.example.demo.dao.PmsSkuSaleAttrValueDao;
import com.example.demo.entity.PmsSkuAttrValue;
import com.example.demo.entity.PmsSkuImage;
import com.example.demo.entity.PmsSkuInfo;
import com.example.demo.entity.PmsSkuSaleAttrValue;
import com.example.demo.service.SkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Admin
 * @title: SkuServiceImpl
 * @projectName demo
 * @description: TODO
 * @date 2020/2/2610:29
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Resource
    private PmsSkuInfoDao pmsSkuInfoDao;
    @Resource
    private PmsSkuImageDao pmsSkuImageDao;
    @Resource
    private PmsSkuAttrValueDao pmsSkuAttrValueDao;
    @Resource
    private PmsSkuSaleAttrValueDao pmsSkuSaleAttrValueDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        if (null == pmsSkuInfo.getId()) {
            //新增
            return addSku(pmsSkuInfo);
        } else {
            //修改
            return updateSku(pmsSkuInfo);
        }
    }

    @Override
    public PmsSkuInfo getSkuInfo(Long skuId) {
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoDao.queryById(skuId);

        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageDao.queryAll(pmsSkuImage);
        pmsSkuInfo.setSkuImageList(pmsSkuImages);

//        @Transient
//        private List<PmsSkuAttrValue> skuAttrValueList;
//        @Transient
//        private List<PmsSkuSaleAttrValue> skuSaleAttrValueList;

        PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
        pmsSkuAttrValue.setSkuId(skuId);
        List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueDao.queryAll(pmsSkuAttrValue);
        pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValues);

        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
        pmsSkuSaleAttrValue.setSkuId(skuId);
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = pmsSkuSaleAttrValueDao.queryAll(pmsSkuSaleAttrValue);
        pmsSkuInfo.setSkuSaleAttrValueList(pmsSkuSaleAttrValues);
        return pmsSkuInfo;
    }

    private boolean updateSku(PmsSkuInfo pmsSkuInfo) {
        return false;

    }

    private boolean addSku(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());

        if (!CollectionUtils.isEmpty(pmsSkuInfo.getSkuImageList()) && null != pmsSkuInfo.getSkuImageList().get(0)) {
            pmsSkuInfo.setSkuDefaultImg(pmsSkuInfo.getSkuImageList().get(0).getImgUrl());
        }
        pmsSkuInfoDao.insert(pmsSkuInfo);
        Long id = pmsSkuInfo.getId();
        if (null == id || id <= 0) {
            throw new RuntimeException("回滚");
        }
        //保存图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        if (!CollectionUtils.isEmpty(skuImageList)) {
            for (PmsSkuImage pmsSkuImage : skuImageList) {
                pmsSkuImage.setSkuId(id);
                int insert = pmsSkuImageDao.insert(pmsSkuImage);
                if (insert == 0)
                    throw new RuntimeException("回滚");
            }
        }

        //保存sku属性值
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)) {
            for (PmsSkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(id);
                int insert = pmsSkuAttrValueDao.insert(skuAttrValue);
                if (insert == 0)
                    throw new RuntimeException("回滚");
            }
        }
        //保存sku销售属性值
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        if (!CollectionUtils.isEmpty(skuSaleAttrValueList)) {
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                pmsSkuSaleAttrValue.setSkuId(id);
                int insert = pmsSkuSaleAttrValueDao.insert(pmsSkuSaleAttrValue);
                if (insert == 0)
                    throw new RuntimeException("回滚");
            }
        }
        return true;
    }
}
