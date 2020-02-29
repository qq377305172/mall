package com.example.demo.service;

import com.example.demo.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author Whyn
 * @date 2020/2/20 18:27
 */
public interface AttrService {
    List<PmsBaseAttrInfo> queryAllByCatalog(Long catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueListByAttrId(Long attrId);

    List<PmsProductSaleAttr> getSpuSaleAttrListBySpuId(Long spuId);

    List<PmsProductImage> getSpuImageListBySpuId(Long spuId);

    List<PmsProductSaleAttr> getAttrInfoBySkuId(Long id, Long skuId);

    String getSkuSaleAttrValueInfos(Long productId);
}
