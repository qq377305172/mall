package com.example.demo.dao;

import com.example.demo.entity.PmsBaseSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PmsBaseSaleAttr)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PmsBaseSaleAttrDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsBaseSaleAttr queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsBaseSaleAttr> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBaseSaleAttr 实例对象
     * @return 对象列表
     */
    List<PmsBaseSaleAttr> queryAll(PmsBaseSaleAttr pmsBaseSaleAttr);

    /**
     * 新增数据
     *
     * @param pmsBaseSaleAttr 实例对象
     * @return 影响行数
     */
    int insert(PmsBaseSaleAttr pmsBaseSaleAttr);

    /**
     * 修改数据
     *
     * @param pmsBaseSaleAttr 实例对象
     * @return 影响行数
     */
    int update(PmsBaseSaleAttr pmsBaseSaleAttr);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}