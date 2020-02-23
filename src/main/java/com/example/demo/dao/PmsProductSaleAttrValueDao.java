package com.example.demo.dao;

import com.example.demo.entity.PmsProductSaleAttrValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spué”€å”®å±žæ€§å€¼(PmsProductSaleAttrValue)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:45
 */
public interface PmsProductSaleAttrValueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsProductSaleAttrValue queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsProductSaleAttrValue> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsProductSaleAttrValue 实例对象
     * @return 对象列表
     */
    List<PmsProductSaleAttrValue> queryAll(PmsProductSaleAttrValue pmsProductSaleAttrValue);

    /**
     * 新增数据
     *
     * @param pmsProductSaleAttrValue 实例对象
     * @return 影响行数
     */
    int insert(PmsProductSaleAttrValue pmsProductSaleAttrValue);

    /**
     * 修改数据
     *
     * @param pmsProductSaleAttrValue 实例对象
     * @return 影响行数
     */
    int update(PmsProductSaleAttrValue pmsProductSaleAttrValue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}