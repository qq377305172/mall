package com.example.demo.service;

import com.example.demo.entity.PmsBaseAttrInfo;
import com.example.demo.entity.PmsBaseAttrValue;
import com.example.demo.entity.PmsBaseSaleAttr;

import java.util.List;

/**
 * @author Whyn
 * @date 2020/2/20 18:27
 */
public interface AttrService {
    List<PmsBaseAttrInfo> queryAllByCatalog(Long catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueListByAttrId(Long attrId);
}
