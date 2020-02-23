package com.example.demo.dao;

import com.example.demo.entity.PmsBaseCatalog2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PmsBaseCatalog2)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PmsBaseCatalog2Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsBaseCatalog2 queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsBaseCatalog2> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBaseCatalog2 实例对象
     * @return 对象列表
     */
    List<PmsBaseCatalog2> queryAll(PmsBaseCatalog2 pmsBaseCatalog2);

    /**
     * 新增数据
     *
     * @param pmsBaseCatalog2 实例对象
     * @return 影响行数
     */
    int insert(PmsBaseCatalog2 pmsBaseCatalog2);

    /**
     * 修改数据
     *
     * @param pmsBaseCatalog2 实例对象
     * @return 影响行数
     */
    int update(PmsBaseCatalog2 pmsBaseCatalog2);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<PmsBaseCatalog2> queryByParent(Integer id);
}