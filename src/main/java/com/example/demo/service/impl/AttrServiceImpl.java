package com.example.demo.service.impl;

import com.example.demo.dao.PmsBaseAttrInfoDao;
import com.example.demo.dao.PmsBaseAttrValueDao;
import com.example.demo.dao.PmsBaseSaleAttrDao;
import com.example.demo.entity.PmsBaseAttrInfo;
import com.example.demo.entity.PmsBaseAttrValue;
import com.example.demo.entity.PmsBaseSaleAttr;
import com.example.demo.service.AttrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Whyn
 * @date 2020/2/20 18:28
 */
@Service("attrService")
public class AttrServiceImpl implements AttrService {
    Logger logger = LoggerFactory.getLogger(AttrServiceImpl.class);
    @Resource
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Resource
    PmsBaseSaleAttrDao pmsBaseSaleAttrDao;
    @Resource
    PmsBaseAttrValueDao pmsBaseAttrValueDao;

    @Override
    public List<PmsBaseAttrInfo> queryAllByCatalog(Long catalog3Id) {
        return pmsBaseAttrInfoDao.queryAllByCatalog(catalog3Id);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrDao.queryAll(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        Long id = pmsBaseAttrInfo.getId();
        if (null == id) {
            //新增
            return insertInfo(pmsBaseAttrInfo);
        } else {
//            修改
            return updateInfo(pmsBaseAttrInfo);
        }
    }

    private int updateInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        int result = pmsBaseAttrInfoDao.update(pmsBaseAttrInfo);
        if (result != 1) {
            logger.error("更新属性失败,执行回滚操作");
            throw new RuntimeException();
        }
        //先删除该属性信息关联的属性值
        PmsBaseAttrValue deleteValues = new PmsBaseAttrValue();
        deleteValues.setAttrId(pmsBaseAttrInfo.getId());
        pmsBaseAttrValueDao.delete(deleteValues);
        //再插入新的属性值
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        if (!StringUtils.isEmpty(attrValueList)) {
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                int insertCount = pmsBaseAttrValueDao.insert(pmsBaseAttrValue);
                result = insertCount == 1 ? 1 : 0;
//                Long valueId = pmsBaseAttrValue.getId();
//                if (null == valueId) {
//                    //新增
//                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
//                    int i = pmsBaseAttrValueDao.insert(pmsBaseAttrValue);
//                    result = i == 1 ? 1 : 0;
//                } else {
////                    修改
//                    int u = pmsBaseAttrValueDao.update(pmsBaseAttrValue);
//                    result = u == 1 ? 1 : 0;
//                }
            }
        }
        return result;

    }

    private int insertInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        Long id = pmsBaseAttrInfoDao.insert(pmsBaseAttrInfo);
        if (null == id || id <= 0) {
            logger.error("插入属性名称失败,执行回滚操作");
            throw new RuntimeException();
        }
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        if (!CollectionUtils.isEmpty(attrValueList))
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(id);
                pmsBaseAttrValueDao.insert(pmsBaseAttrValue);
            }
        return 1;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueListByAttrId(Long attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return pmsBaseAttrValueDao.queryAll(pmsBaseAttrValue);
    }
}
