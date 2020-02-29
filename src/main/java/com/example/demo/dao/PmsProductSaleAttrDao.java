package com.example.demo.dao;

import com.example.demo.entity.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PmsProductSaleAttr)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:45
 */
public interface PmsProductSaleAttrDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsProductSaleAttr queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PmsProductSaleAttr> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsProductSaleAttr 实例对象
     * @return 对象列表
     */
    List<PmsProductSaleAttr> queryAll(PmsProductSaleAttr pmsProductSaleAttr);

    /**
     * 新增数据
     *
     * @param pmsProductSaleAttr 实例对象
     * @return 影响行数
     */
    int insert(PmsProductSaleAttr pmsProductSaleAttr);

    /**
     * 修改数据
     *
     * @param pmsProductSaleAttr 实例对象
     * @return 影响行数
     */
    int update(PmsProductSaleAttr pmsProductSaleAttr);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<PmsProductSaleAttr> querySaleAttrByChecked(@Param("skuId") Long skuId, @Param("productId") Long productId);
}