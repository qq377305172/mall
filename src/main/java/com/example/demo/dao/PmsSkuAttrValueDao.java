package com.example.demo.dao;

import com.example.demo.entity.PmsSkuAttrValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * skuå¹³å°å±žæ€§å€¼å…³è”è¡¨(PmsSkuAttrValue)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:45
 */
public interface PmsSkuAttrValueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsSkuAttrValue queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsSkuAttrValue> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsSkuAttrValue 实例对象
     * @return 对象列表
     */
    List<PmsSkuAttrValue> queryAll(PmsSkuAttrValue pmsSkuAttrValue);

    /**
     * 新增数据
     *
     * @param pmsSkuAttrValue 实例对象
     * @return 影响行数
     */
    int insert(PmsSkuAttrValue pmsSkuAttrValue);

    /**
     * 修改数据
     *
     * @param pmsSkuAttrValue 实例对象
     * @return 影响行数
     */
    int update(PmsSkuAttrValue pmsSkuAttrValue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}